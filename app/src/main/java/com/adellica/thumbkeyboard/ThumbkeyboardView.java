package com.adellica.thumbkeyboard;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by klm on 9/27/16.
 */
public class ThumbkeyboardView extends View {
    private static final String TAG = "TKEY";
    public ThumbkeyboardIME Ime;
    private boolean showHelp = false;
    private final int MAX_DELAY_DOUBLE_COMBO = 60; // ms
    private static final int BLOB_RADIUS = 40; // dpi
    private static final int BLOB_BORDER = 4; // dpi

    private static class MutableDouble {
        public double value;

        public MutableDouble(double i) {
            this.value = i;
        }
    }

    private static class Modifiers {
        public final static int
                Shift = 0x01,
                Ctrl = 0x02,
                Alt = 0x04,
                Meta = 0x08; // I don't now what this one is but it's in KeyEvent. Maybe win/option/command
    }

    private int _ModifierMask = 0;
    private void modifiersClear() {
        _ModifierMask = 0;
    }

    private void modShift(boolean down_p) { _ModifierMask = down_p?(_ModifierMask|Modifiers.Shift):(_ModifierMask&~Modifiers.Shift); }
    private void modCtrl(boolean down_p)  { _ModifierMask = down_p?(_ModifierMask|Modifiers.Ctrl):(_ModifierMask&~Modifiers.Ctrl); }
    private void modAlt(boolean down_p)   { _ModifierMask = down_p?(_ModifierMask|Modifiers.Alt):(_ModifierMask&~Modifiers.Alt); }
    private void modMeta(boolean down_p)  { _ModifierMask = down_p?(_ModifierMask|Modifiers.Meta):(_ModifierMask&~Modifiers.Meta); }
    private  boolean modMeta()  { return (_ModifierMask & Modifiers.Meta) != 0;  }
    private  boolean modAlt()   { return (_ModifierMask & Modifiers.Alt) != 0;  }
    private  boolean modCtrl()  { return (_ModifierMask & Modifiers.Ctrl) != 0;  }
    private  boolean modShift() { return (_ModifierMask & Modifiers.Shift) != 0;  }

    public ThumbkeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    class Blob {
        // dpi coordinates (negative counts from right/bottom)
        final private int col;
        final private int row;
        final private int bid;
        public boolean holding = false;
        public boolean tapping = false;


        final Paint fill = new Paint();

        Blob(int bid, int col, int row) {
            this.bid = bid;
            this.col = col;
            this.row = row;
            this.fill.setStyle(Paint.Style.FILL);
        }
        public double dist2(double x, double y) {
            return  (this.x() - x) * (this.x() - x) +
                    (this.y() - y) * (this.y() - y);
        }

        // screen coordinates
        public float x() { return (col < 0 ? getWidth() : 0) + pixels(col * (BS * 2) + BS); }
        public float y() { return getHeight() - (pixels((2 - row) * (BS * 2) + BS)); }

        public int bid() { return bid; }

        public void draw(Canvas canvas, boolean anybody_up) {
            if(anybody_up)
                if(holding) fill.setColor(Color.argb(0xB0, 0x00, 0x80, 0xff));
                else        fill.setColor(Color.argb(0x40, 0x00, 0xff, 0xff));
            else            fill.setColor(Color.argb(0x40, 0x00, 0xff, 0xff));

            final int S = pixels(BLOB_RADIUS - BLOB_BORDER);
            canvas.drawRect(x()-S, y()-S, x()+S, y()+S, fill);
        }
    }




    private int _anchorY = -100;
    private int anchorY() {
        if(_anchorY >= 0)
            return _anchorY;
        else
            return getHeight() + _anchorY;
    }

    final int BS = BLOB_RADIUS;
    static final int BB = BLOB_BORDER * 2; // wall margin
    // negative positions means right/bottom-aligned
    Blob [] _blobs = new Blob [] {
            //       idx col row
            new Blob( 0,  0, 0),
            new Blob( 1,  1, 0),
            new Blob( 2, -2, 0),
            new Blob( 3, -1, 0),
            new Blob( 4,  0, 1),
            new Blob( 5,  1, 1),
            new Blob( 6, -2, 1),
            new Blob( 7, -1, 1),
            new Blob( 8,  0, 2),
            new Blob( 9,  1, 2),
            new Blob(10, -2, 2),
            new Blob(11, -1, 2),
    };
    private Blob [] blobs () { return _blobs; }


    class Stroke {
        public int taps[] = new int[blobs().length];
        public int ups[] = new int[blobs().length];
        public int downs[] = new int[blobs().length];
        public int lefts[] = new int[blobs().length];
        public int rights[] = new int[blobs().length];

        public void clear() {
            for(int i = 0 ; i < taps.length ; i++) {
                taps[i] = 0;
                ups[i] = 0;
                downs[i] = 0;
                lefts[i] = 0;
                rights[i] = 0;
            }
        }

        @Override
        public String toString() {
            String s = "";
            for(int j = 0 ; j < taps.length ; j += 4) {
                s += ""
                    + taps[j + 0] + lefts[j+0] + ups[j+0] + downs[j+0] + rights[j+0] + "-"
                    + taps[j + 1] + lefts[j+1] + ups[j+1] + downs[j+1] + rights[j+1]
                    + " "
                    + taps[j + 2] + lefts[j+2] + ups[j+2] + downs[j+2] + rights[j+2] + "-"
                    + taps[j + 3] + lefts[j+3] + ups[j+3] + downs[j+3] + rights[j+3]
                    + "\n";
            }
            return s;
        }
    }
    Stroke stroke = new Stroke();

    private int touch2blob(double x, double y, MutableDouble closestDist) {
        int nearest = 0; // index
        double dist2 = blobs()[nearest].dist2(x, y);
        for(int bid = 1 ; bid < blobs().length ; bid++) {
            double dist = blobs()[bid].dist2(x, y);
            if(dist < dist2) {
                dist2 = dist;
                nearest = bid;
            }
        }
        if(closestDist != null)
            closestDist.value = Math.sqrt(dist2);
        return nearest;
    }

    private int touch2blob(double x, double y) {
        return touch2blob(x, y, null);
    }


    private void handlePattern(String p) {
        if(p != null) {
            String token = ThumboardLayout.parse(p);
            if(token != null)
                handleToken(token);
        }
    }

    /**
     * @param token
     * @return just the command part of a token
     */
    private String cmd(String token) {
        int idx = token.indexOf(' ');

        if(idx >= 0)
            return token.substring(0, idx);
        else
            // no space means we've got an unparamterized command
            return token;
    }

    /**
     *
     * @param token
     * @return just the arguments/value part of a token
     */
    private String value(String token) {
        int idx = token.indexOf(' ');
        if(idx >= 0)
            return token.substring(idx + 1);
        else
            // no space means we've got an unparameterized command
            return null;
    }


    private String lastToken;
    private void handleToken(String t) {
        String cmd = cmd(t);

        if("shift".equals(cmd)) {
            handleShift(value(t));
        } else if("ctrl".equals(cmd)) {
            handleCtrl(value(t));
        } else if("alt".equals(cmd)) {
            handleAlt(value(t));
        } else if("meta".equals(cmd)) {
            handleMeta(value(t));
        } else if ("help".equals(cmd)) {
            showHelp = !showHelp;
            postInvalidate();
        } else if("repeat".equals(cmd)) {
            if(lastToken != null) {
                Log.d(TAG, "repeating with " + lastToken);
                if (!"repeat".equals(cmd(lastToken))) {
                    handleToken(lastToken);
                } else
                    Log.e(TAG, "error! trying to repeat the repeat command!");
            }
        } else if ("key".equals(cmd)) {
            handleKey(value(t));
        } else if ("input".equals(cmd)) {
            handleInput(value(t));
        }

        if (!"repeat".equals(cmd)) // avoid infinite recursion
            lastToken = t;
    }

    private void handleShift(String param) {
        if(param == null || "".equals(param))
            modShift(!modShift());
        else {
            boolean old = modShift();
            modShift(true);
            handleToken("key " + param);
            modShift(old);
        }
    }

    private void handleCtrl(String param) {
        if(param == null || "".equals(param))
            modCtrl(!modCtrl());
        else {
            boolean old = modCtrl();
            modCtrl(true);
            handleToken("key " + param);
            modCtrl(old);
        }
    }

    private void handleAlt(String param) {
        if(param == null || "".equals(param))
            modAlt(!modAlt());
        else {
            boolean old = modAlt();
            modAlt(true);
            handleToken("key " + param);
            modAlt(old);
        }
    }

    private void handleMeta(String param) {
        if(param == null || "".equals(param))
            modMeta(!modMeta());
        else {
            boolean old = modMeta();
            modMeta(true);
            handleToken("key " + param);
            modMeta(old);
        }
    }

    private void handleInput(String input) {
        if(modShift())
            Ime.getCurrentInputConnection().commitText(input.toUpperCase(), 0);
        else
            Ime.getCurrentInputConnection().commitText(input, 0);

        modifiersClear();
    }

    private int getMetaState() {
        int meta = 0;
        if (modShift()) meta |= KeyEvent.META_SHIFT_ON | KeyEvent.META_SHIFT_LEFT_ON;
        if (modCtrl())  meta |= KeyEvent.META_CTRL_ON  | KeyEvent.META_CTRL_LEFT_ON;
        if (modAlt())   meta |= KeyEvent.META_ALT_ON   | KeyEvent.META_ALT_LEFT_ON;
        if (modMeta())  meta |= KeyEvent.META_META_ON  | KeyEvent.META_META_LEFT_ON;
        return meta;
    }

    private void handleKey(String key) {

        final int keycode = ThumboardKeycodes.string2keycode(key);
        int meta = getMetaState();
        if (keycode != 0) {
            long now = System.currentTimeMillis();
            Ime.getCurrentInputConnection().sendKeyEvent(new KeyEvent(now, now, KeyEvent.ACTION_DOWN, keycode, 0, meta));
            Ime.getCurrentInputConnection().sendKeyEvent(new KeyEvent(now, now, KeyEvent.ACTION_UP,   keycode, 0, meta));
        }

        modifiersClear();
    }

    boolean holding = false;
    Blob [] fingerTouches = new Blob [ 4 ]; // who'se got 4 thumbs anyway?

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int i = event.getActionIndex(); // index of finger that caused the down event

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: holding = true; break;
            case MotionEvent.ACTION_UP: holding = false; break;
        }

        if(event.getPointerId(i) >= fingerTouches.length) {
            Log.e(TAG, "only up to " + fingerTouches.length + " simultanious fingers supported ...");
            return false;
        }

        MutableDouble d = new MutableDouble(-1);
        touch2blob(event.getX(i), event.getY(i), d);
        if(d.value > pixels((int)(BLOB_RADIUS * 1.5))) { // min diameter to register a button click. let's be tolerant!
            _anchorY = (int)event.getY(i);
            postInvalidate();
            return true;
        }

        switch(event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN: { // primary finger down!
                final int btn = touch2blob(event.getX(i), event.getY(i));
                blobs()[btn].tapping = true;
                break; }

            case MotionEvent.ACTION_POINTER_DOWN: { // another finger down while holding one down
                final int btn = touch2blob(event.getX(i), event.getY(i));
                blobs()[btn].tapping = true;
                break; }

            case MotionEvent.ACTION_POINTER_UP: { // finger up, still holding one down
                final int btn = touch2blob(event.getX(i), event.getY(i));
                if(blobs()[btn].tapping)
                    stroke.taps[btn]++;
                break; }
            case MotionEvent.ACTION_MOVE: {
                for( int j = 0 ; j < event.getPointerCount() ; j++) {
                    final Blob btn = blobs()[touch2blob(event.getX(j), event.getY(j))]; // <-- going to
                    if(btn != fingerTouches[event.getPointerId(j)]) {
                        final int fid = event.getPointerId(j);
                        final Blob old = fingerTouches[fid]; // <-- coming from
                        if(old != null) {
                            final int bid = old.bid();
                            int ox = old.bid() % 4, oy = old.bid() / 4;
                            int nx = btn.bid() % 4, ny = btn.bid() / 4;
                            int dx = nx - ox, dy = ny - oy;
                            Log.i(TAG, "swipe on " + bid + ": " + dx + "," + dy);
                            int table [] = (dx == 0
                                    ? (dy == 1 ? stroke.downs : stroke.ups)
                                    : (dx == 1 ? stroke.rights : stroke.lefts));
                            table[bid]++;
                            old.tapping = false; // this is no longer a tap
                        }
                        fingerTouches[fid] = btn;
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP: { // all fingers up! obs: last finger up ?≠ first finger down
                final int btn = touch2blob(event.getX(i), event.getY(i));
                if(blobs()[btn].tapping)
                    stroke.taps[btn]++;
                Log.i(TAG, "===============================");
                Log.i(TAG, "" + stroke);
                stroke.clear();
                for(int j = 0 ; j < fingerTouches.length ; j++) fingerTouches[j] = null;
                for(int j = 0 ; j < blobs().length ; j++) blobs()[j].tapping = false;
                break; }
            default:
                //Log.d(TAG, "missed event " + event.getActionMasked());
                break;
        }
        return true;
    }

    private int pixels(int dpi) {
        Resources r = getResources();
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpi, r.getDisplayMetrics());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final Paint cp1 = new Paint(), cp2 = new Paint();
        cp1.setColor(Color.argb(0x80, 0, 0, 0));
        cp1.setPathEffect(new DashPathEffect(new float[]{7, 7}, 0));
        cp1.setStyle(Paint.Style.STROKE);
        cp1.setStrokeWidth(4);
        cp2.setColor(Color.argb(0x80, 0xff, 0xff, 0xff));
        cp2.setPathEffect(new DashPathEffect(new float[]{7, 7}, 7));
        cp2.setStyle(Paint.Style.STROKE);
        cp2.setStrokeWidth(4);

        Blob bs [] = blobs();
        boolean any = false;

        for (int i = 0 ; i < bs.length ; i++) {
           bs[i].draw(canvas, any);
        }

        if(!showHelp) return;

        final TextPaint p = new TextPaint();
        final int w = canvas.getWidth();
        final int h = canvas.getHeight();
        final int y = Math.min(w, h);

        final Paint background = new Paint();
        background.setColor(Color.argb(0xC0, 20, 20, 20));


        StaticLayout textLayout = new StaticLayout(ThumboardLayout.help(), p, canvas.getWidth() / 3, Layout.Alignment.ALIGN_NORMAL, 1.8f, 0.0f, false);

        p.setTypeface(Typeface.MONOSPACE);
        p.setTextSize(y / 36);

        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.rgb(255, 255, 255));
        canvas.save();
        canvas.translate(450, 0);
        canvas.drawRect(0, 0, textLayout.getWidth() + 100, textLayout.getHeight() + 100, background);
        canvas.translate(50, 20);
        textLayout.draw(canvas);
        canvas.restore();


    }

}
