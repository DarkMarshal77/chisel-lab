import chisel3._

class Delay extends Module {
  val io = IO(new Bundle {
    val din = Input(UInt(8.W))
    val dout = Output(UInt(8.W))
  })

  val res = Wire(UInt())

  // ***** your code starts here *****

  val firstReg = RegNext(io.din)
  val secondReg = RegNext(firstReg)
  res := secondReg

  // ***** your code ends here *****

  io.dout := res
}