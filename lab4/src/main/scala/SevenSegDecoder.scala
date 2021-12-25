import chisel3._
import chisel3.util._

class SevenSegDecoder extends Module {
  val io = IO(new Bundle {
    val sw = Input(UInt(4.W))
    val seg = Output(UInt(7.W))
    val an = Output(UInt(4.W))
  })

  val sevSeg = WireInit(0.U)

  // ***** your code starts here *****

  val top = WireInit(0x01.U(7.W))
  val topRight = WireInit(0x02.U(7.W))
  val lowerRight = WireInit(0x04.U(7.W))
  val lower = WireInit(0x08.U(7.W))
  val lowerLeft = WireInit(0x10.U(7.W))
  val topLeft = WireInit(0x20.U(7.W))
  val middle = WireInit(0x40.U(7.W))

  switch(io.sw) {
    is (0.U) { sevSeg := top | topRight | lowerRight | lower | lowerLeft | topLeft }
    is (1.U) { sevSeg := topRight | lowerRight }
    is (2.U) { sevSeg := top | topRight | middle | lower | lowerLeft }
    is (3.U) { sevSeg := top | topRight | lowerRight | lower | middle }
    is (4.U) { sevSeg := middle | topRight | lowerRight | topLeft }
    is (5.U) { sevSeg := top | lowerRight | lower | middle | topLeft }
    is (6.U) { sevSeg := top | middle | lowerRight | lower | lowerLeft | topLeft }
    is (7.U) { sevSeg := top | topRight | lowerRight }
    is (8.U) { sevSeg := top | topRight | lowerRight | lower | lowerLeft | topLeft | middle }
    is (9.U) { sevSeg := top | topRight | lowerRight | middle | lower | topLeft }
    is (10.U) { sevSeg := top | topRight | lowerRight | middle | lowerLeft | topLeft }
    is (11.U) { sevSeg := lowerRight | lower | lowerLeft | topLeft | middle }
    is (12.U) { sevSeg := top | lower | lowerLeft | topLeft }
    is (13.U) { sevSeg := topRight | lowerRight | lower | lowerLeft | middle }
    is (14.U) { sevSeg := top | lower | lowerLeft | topLeft | middle }
    is (15.U) { sevSeg := top | middle | lowerLeft | topLeft }
  }

  // ***** your code ends here *****

  io.seg := ~sevSeg
  io.an := "b1110".U
}

// generate Verilog
object SevenSegDecoder extends App {
  (new chisel3.stage.ChiselStage).emitVerilog(new SevenSegDecoder())

}


