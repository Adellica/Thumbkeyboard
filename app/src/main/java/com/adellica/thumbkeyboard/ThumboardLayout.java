// generated from gen-ThumboardLayout.java.scm and layout.scm
package com.adellica.thumbkeyboard;
public class ThumboardLayout {
  /** returns string-representation of key as defined by KeyEvent KEYCODE_'s.
  */
  public static String parse(String p) {
    if(false) {}

    else if(p.matches("(?s)\\.\\.\\.\\.\\. \\.\\.xx\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "help";
    // unused ("x.. ...")
    else if(p.matches("(?s)\\.\\.\\.x\\. \\.\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key E";
    else if(p.matches("(?s)x\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key A";
    else if(p.matches("(?s)\\.\\.\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "repeat";
    else if(p.matches("(?s)\\.\\.\\.\\.\\. \\.\\.\\.x\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key SPACE";
    else if(p.matches("(?s)\\.\\.\\.\\.\\. x\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key T";
    else if(p.matches("(?s)\\.\\.x\\.\\. \\.\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key O";
    else if(p.matches("(?s)\\.x\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key I";
    else if(p.matches("(?s)\\.\\.\\.\\.\\. \\.\\.x\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key N";
    else if(p.matches("(?s)\\.\\.\\.\\.\\. \\.x\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key S";
    else if(p.matches("(?s)\\.\\.\\.\\.x \\.\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key H";
    else if(p.matches("(?s)\\.\\.\\.x\\. \\.x\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key R";
    else if(p.matches("(?s)\\.\\.\\.\\.x \\.x\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key D";
    else if(p.matches("(?s)\\.\\.\\.x\\. x\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key L";
    else if(p.matches("(?s)\\.\\.\\.\\.x x\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key C";
    else if(p.matches("(?s)\\.\\.x\\.\\. x\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key U";
    else if(p.matches("(?s)\\.x\\.\\.\\. \\.x\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key M";
    else if(p.matches("(?s)\\.\\.\\.\\.x \\.\\.x\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key K";
    else if(p.matches("(?s)\\.x\\.\\.\\. x\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key W";
    else if(p.matches("(?s)\\.\\.x\\.\\. \\.x\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key F";
    else if(p.matches("(?s)\\.\\.x\\.\\. \\.\\.x\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key J";
    else if(p.matches("(?s)\\.\\.\\.x\\. \\.\\.x\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key G";
    else if(p.matches("(?s)\\.x\\.\\.\\. \\.\\.x\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key Y";
    else if(p.matches("(?s)\\.\\.\\.x\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key P";
    else if(p.matches("(?s)\\.x\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key B";
    else if(p.matches("(?s)\\.\\.\\.\\.x \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key V";
    else if(p.matches("(?s)\\.\\.x\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key Q";
    else if(p.matches("(?s)\\.\\.\\.x\\. \\.\\.\\.\\.\\.\\n\\.\\.\\.x\\. \\.x\\.\\.\\.\\n\\.\\.\\.x\\. \\.\\.\\.\\.\\.\\n\\.\\.\\.x\\. \\.x\\.\\.\\.\\n\\.\\.\\.x\\. \\.\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key X";
    else if(p.matches("(?s)\\.\\.\\.x\\. \\.\\.\\.\\.\\.\\n\\.\\.\\.x\\. x\\.\\.\\.\\.\\n\\.\\.\\.x\\. \\.\\.\\.\\.\\.\\n\\.\\.\\.x\\. x\\.\\.\\.\\.\\n\\.\\.\\.x\\. \\.\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key Z";
    else if(p.matches("(?s)x\\.\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "shift";
    else if(p.matches("(?s)\\.\\.\\.\\.\\. \\.\\.\\.\\.x\\nx\\.\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "shift";
    else if(p.matches("(?s)\\.\\.\\.\\.\\. \\.\\.\\.\\.x\\nx\\.\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "shift";
    else if(p.matches("(?s)x\\.\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "shift";
    else if(p.matches("(?s)\\.\\.\\.\\.\\. \\.\\.\\.\\.x\\n\\.x\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "ctrl";
    else if(p.matches("(?s)\\.\\.\\.\\.\\. \\.\\.\\.\\.x\\n\\.x\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "ctrl";
    else if(p.matches("(?s)\\.\\.\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.x\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "alt";
    else if(p.matches("(?s)\\.\\.\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.x\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "alt";
    else if(p.matches("(?s)\\.\\.\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.x \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "meta";
    else if(p.matches("(?s)\\.\\.\\.\\.\\. \\.\\.\\.\\.x\\n\\.\\.\\.\\.x \\.\\.\\.\\.x\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "meta";
    else if(p.matches("(?s)(.)*\\.x\\.\\.\\. \\.\\.\\.x\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.x\\.\\n" )) return "key DEL";
    else if(p.matches("(?s)(.)*\\.\\.\\.\\.x \\.\\.\\.x\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.x\\.\\n" )) return "key ENTER";
    else if(p.matches("(?s)(.)*\\.\\.x\\.\\. \\.\\.\\.x\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.x\\.\\n" )) return "key TAB";
    else if(p.matches("(?s)(.)*x\\.\\.\\.\\. \\.\\.\\.\\.x\\nx\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key DPAD_UP";
    else if(p.matches("(?s)(.)*x\\.\\.\\.\\. \\.\\.\\.x\\.\\nx\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key DPAD_DOWN";
    else if(p.matches("(?s)(.)*x\\.\\.\\.\\. x\\.\\.\\.\\.\\nx\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key DPAD_LEFT";
    else if(p.matches("(?s)(.)*x\\.\\.\\.\\. \\.\\.x\\.\\.\\nx\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "key DPAD_RIGHT";
    else if(p.matches("(?s)\\.x\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.x\\.\\.\\. \\.x\\.\\.\\.\\n\\.x\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "input .";
    else if(p.matches("(?s)\\.x\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.x\\.\\.\\. \\.x\\.\\.\\.\\n\\.x\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.x\\.\\.\\. \\.x\\.\\.\\.\\n\\.x\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "input :";
    else if(p.matches("(?s)\\.x\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.x\\.\\.\\. x\\.\\.\\.\\.\\n\\.x\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "input ,";
    else if(p.matches("(?s)\\.x\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.x\\.\\.\\. x\\.\\.\\.\\.\\n\\.x\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.x\\.\\.\\. x\\.\\.\\.\\.\\n\\.x\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "input ;";
    else if(p.matches("(?s)\\.x\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.x\\.\\.\\. \\.\\.\\.x\\.\\n\\.x\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "input !";
    else if(p.matches("(?s)\\.x\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.x\\.\\.\\. \\.\\.\\.x\\.\\n\\.x\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.x\\.\\.\\. \\.\\.\\.x\\.\\n\\.x\\.\\.\\. \\.\\.\\.\\.\\.\\n\\.\\.\\.\\.\\. \\.\\.\\.\\.\\.\\n" )) return "input ?";
    return null;
  }

  public static String help() {
    return "      (help) ..... ..xx.\n           E ...x. .....\n           A x.... .....\n    (repeat) ..... ....x\n       SPACE ..... ...x.\n           T ..... x....\n           O ..x.. .....\n           I .x... .....\n           N ..... ..x..\n           S ..... .x...\n           H ....x .....\n           R ...x. .x...\n           D ....x .x...\n           L ...x. x....\n           C ....x x....\n           U ..x.. x....\n           M .x... .x...\n           K ....x ..x..\n           W .x... x....\n           F ..x.. .x...\n           J ..x.. ..x..\n           G ...x. ..x..\n           Y .x... ..x..\n           P ...x. ....x\n           B .x... ....x\n           V ....x ....x\n           Q ..x.. ....x\n           X ...x. .....\n             ...x. .x...\n             ...x. .....\n             ...x. .x...\n             ...x. .....\n           Z ...x. .....\n             ...x. x....\n             ...x. .....\n             ...x. x....\n             ...x. .....\n     (shift) x.... ....x\n     (shift) ..... ....x\n             x.... ....x\n             ..... ....x\n     (shift) ..... ....x\n             x.... ....x\n     (shift) x.... ....x\n             ..... ....x\n      (ctrl) ..... ....x\n             .x... ....x\n             ..... ....x\n      (ctrl) ..... ....x\n             .x... ....x\n       (alt) ..... ....x\n             ..x.. ....x\n             ..... ....x\n       (alt) ..... ....x\n             ..x.. ....x\n      (meta) ..... ....x\n             ....x ....x\n             ..... ....x\n      (meta) ..... ....x\n             ....x ....x\n         DEL (: (* any) .x... ...x. ..... ...x.)\n       ENTER (: (* any) ....x ...x. ..... ...x.)\n         TAB (: (* any) ..x.. ...x. ..... ...x.)\n     DPAD_UP (: (* any) x.... ....x x.... .....)\n   DPAD_DOWN (: (* any) x.... ...x. x.... .....)\n   DPAD_LEFT (: (* any) x.... x.... x.... .....)\n  DPAD_RIGHT (: (* any) x.... ..x.. x.... .....)\n           . .x... .....\n             .x... .x...\n             .x... .....\n           : .x... .....\n             .x... .x...\n             .x... .....\n             .x... .x...\n             .x... .....\n           , .x... .....\n             .x... x....\n             .x... .....\n           ; .x... .....\n             .x... x....\n             .x... .....\n             .x... x....\n             .x... .....\n           ! .x... .....\n             .x... ...x.\n             .x... .....\n           ? .x... .....\n             .x... ...x.\n             .x... .....\n             .x... ...x.\n             .x... .....\n";
  }
}
