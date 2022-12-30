import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import observer.UndoableStringBuilder;

import org.junit.jupiter.api.Test;

public class UndoableStringBuildertest {

  @Test
  public void testEMPConstructor() {
    UndoableStringBuilder usbEMPConst = new UndoableStringBuilder();
    assertNotEquals("to be or not to be", usbEMPConst.toString());
  }

  @Test
  public void testCapConstructor() {
    UndoableStringBuilder usbCapConst = new UndoableStringBuilder(18);
    assertNotEquals("to be or not to be", usbCapConst.toString());
  }

  @Test
  public void testStrConstructor() {
    UndoableStringBuilder usbStrConst = new UndoableStringBuilder(
      "to be or not to be"
    );
    assertEquals("to be or not to be", usbStrConst.toString());
  }

  @Test
  public void testChrSeqConstructor() {
    CharSequence chars = "to be or not to be";
    UndoableStringBuilder usbEMPConst = new UndoableStringBuilder(chars);
    assertEquals("to be or not to be", usbEMPConst.toString());
  }

  @Test
  public void testAppend() {
    UndoableStringBuilder usbAppend = new UndoableStringBuilder(
      "to be or not to be"
    );
    assertEquals("to be or not to be", usbAppend.toString());
    usbAppend.append("to be or not to be");
    usbAppend.append("to be or not to be");
    assertEquals(
      "to be or not to beto be or not to beto be or not to be",
      usbAppend.toString()
    );
  }

  @Test
  public void testAppendsec() {
    UndoableStringBuilder usbAppendsec = new UndoableStringBuilder(
      "Let's have fun :)"
    );
    assertNotEquals("to be or not to be", usbAppendsec.toString());
    assertEquals("Let's have fun :)", usbAppendsec.toString());
    usbAppendsec.append(" ");
    usbAppendsec.append("let's have fun :)");
    assertEquals(
      "Let's have fun :) let's have fun :)",
      usbAppendsec.toString()
    );
  }

  @Test
  public void testDelete() {
    UndoableStringBuilder usbDel = new UndoableStringBuilder(
      "Let's have fun :)"
    );
    assertNotEquals("to be or not to be", usbDel.toString());
    assertEquals("Let's have fun :)", usbDel.toString());
    usbDel.append(" ");
    usbDel.append("let's have fun :)");
    assertEquals("Let's have fun :) let's have fun :)", usbDel.toString());
    usbDel.delete(0, 15);
    assertEquals(":) let's have fun :)", usbDel.toString());
  }

  @Test
  public void testDeleteSec() {
    UndoableStringBuilder usbDelSec = new UndoableStringBuilder(
      "Let's have fun :)"
    );
    assertNotEquals("Peace among worlds", usbDelSec.toString());
    assertEquals("Let's have fun :)", usbDelSec.toString());
    usbDelSec.delete(0, 15);
    usbDelSec.append("Peace among worlds");
    assertNotEquals(":) let's have fun :)", usbDelSec.toString());
    assertEquals(":)Peace among worlds", usbDelSec.toString());
  }

  @Test
  public void testInsert() {
    UndoableStringBuilder usbIns = new UndoableStringBuilder(
      "Let's have fun :)"
    );
    assertNotEquals("Peace among worlds", usbIns.toString());
    assertEquals("Let's have fun :)", usbIns.toString());
    usbIns.insert(0, "Let's get some ice cream And ");
    assertEquals(
      "Let's get some ice cream And Let's have fun :)",
      usbIns.toString()
    );
  }

  @Test
  public void testInsertSec() {
    UndoableStringBuilder usbInsSec = new UndoableStringBuilder(
      "Let's have fun :)"
    );
    assertNotEquals("Peace among worlds", usbInsSec.toString());
    assertEquals("Let's have fun :)", usbInsSec.toString());
    usbInsSec.insert(0, "Let's get some ice cream And ");
    usbInsSec.delete(0, 25);
    assertEquals("And Let's have fun :)", usbInsSec.toString());
  }

  @Test
  public void testReplace() {
    UndoableStringBuilder usbRpc = new UndoableStringBuilder(
      "Let's have fun :)"
    );
    assertNotEquals("Peace among worlds", usbRpc.toString());
    assertEquals("Let's have fun :)", usbRpc.toString());
    usbRpc.insert(0, "Let's get some ice cream And ");
    usbRpc.delete(0, 25);
    assertEquals("And Let's have fun :)", usbRpc.toString());
    usbRpc.replace(0, usbRpc.toString().length(), "Don't Worry Be Happy :)");
    assertEquals("Don't Worry Be Happy :)", usbRpc.toString());
  }

  @Test
  public void testReplaceSec() {
    UndoableStringBuilder usbRpcSec = new UndoableStringBuilder(
      "Don't Worry Be Happy :)"
    );
    usbRpcSec.replace(
      usbRpcSec.toString().length() - 2,
      usbRpcSec.toString().length() + 3,
      "!!"
    );
    assertEquals("Don't Worry Be Happy !!", usbRpcSec.toString());
  }

  @Test
  public void testReverse() {
    UndoableStringBuilder usbRev = new UndoableStringBuilder(
      "Is this the end of our tests?"
    );
    assertEquals("Is this the end of our tests?", usbRev.toString());
    String RevStr = "";
    usbRev.reverse();
    assertEquals("?stset ruo fo dne eht siht sI", usbRev.toString());
    usbRev.reverse();
    assertEquals("Is this the end of our tests?", usbRev.toString());
  }

  @Test
  public void testUndo() {
    UndoableStringBuilder usbUndo = new UndoableStringBuilder(
      "to be or not to be"
    );

    usbUndo.replace(17, 19, "eat");
    usbUndo.reverse();
    usbUndo.undo();
    usbUndo.undo();
    String usb_tobe = usbUndo.toString();
    assertEquals("to be or not to be", usb_tobe);
  }

  @Test
  public void testUndoSec() {
    UndoableStringBuilder usbUndoSec = new UndoableStringBuilder(
      "This is our last TEST ForReal! :)"
    );
    usbUndoSec.append(" Did you have fun with us?");
    assertEquals(
      "This is our last TEST ForReal! :) Did you have fun with us?",
      usbUndoSec.toString()
    );
    usbUndoSec.undo();
    assertEquals("This is our last TEST ForReal! :)", usbUndoSec.toString());
  }
}
