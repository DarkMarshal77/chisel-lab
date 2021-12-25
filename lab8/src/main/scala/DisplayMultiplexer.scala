import chisel3._
import chisel3.util._

class DisplayMultiplexer(maxCount: Int) extends Module {
  val io = IO(new Bundle {
    val sum = Input(UInt(8.W))
    val price = Input(UInt(8.W))
    val seg = Output(UInt(7.W))
    val an = Output(UInt(4.W))
  })

  val sevSeg = WireDefault("b1111111".U(7.W))
  val select = WireDefault("b0001".U(4.W))

  // *** your code starts here

  val decoder = Module(new SevenSegDec())
  val cnt = RegInit(0.U(2.W))
  val value = RegInit(0.U(16.W))
  when (cnt === 0.U) {
    value := Cat(io.sum, io.price)
  }
  decoder.io.in := (value >> (cnt*4.U))(7, 0)
  sevSeg := decoder.io.out
  select := (1.U << cnt)
  cnt := cnt + 1.U

  // *** your code ends here

  io.seg := ~sevSeg
  io.an := ~select
}
