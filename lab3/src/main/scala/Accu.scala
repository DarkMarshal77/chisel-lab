import chisel3._

class Accu extends Module {
  val io = IO(new Bundle {
    val din = Input(UInt(8.W))
    val setZero = Input(Bool())
    val dout = Output(UInt(8.W))
  })

  val res = Wire(UInt())

  // ***** your code starts here *****

  val accReg = RegInit(0.U(8.W))
  accReg := accReg + io.din
  when (io.setZero) {
    accReg := 0.U
  }
  res := accReg

  // ***** your code ends here *****

  io.dout := res
}