package com.qi.management.common

class OrderStatus {
    companion object {
        const val ORDER_WAIT_FINISH = "0"//待完成
        const val ORDER_FINISHED = "1"//已完成
        const val ORDER_REFUND_FINISH = "2"//退款中
        const val ORDER_REFUND_FINISHED = "3"//退款已完成
    }
}
