import chisel3._
import chisel3.util._

class SevenSegDec extends Module {
  val io = IO(new Bundle {
    val in = Input(UInt(4.W))
    val out = Output(UInt(7.W))
  })

  val sevSeg = WireDefault(0.U)

  // *** add your table from Lab 5 here or use the version from Lab 6.

  val top = WireInit(0x01.U(7.W))
  val topRight = WireInit(0x02.U(7.W))
  val lowerRight = WireInit(0x04.U(7.W))
  val lower = WireInit(0x08.U(7.W))
  val lowerLeft = WireInit(0x10.U(7.W))
  val topLeft = WireInit(0x20.U(7.W))
  val middle = WireInit(0x40.U(7.W))

  switch(io.in) {
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

  // *** end adding the table

  io.out := sevSeg
}


