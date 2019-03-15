package com.yizhipin.base.ui.pop

import android.content.Context
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import java.util.*

/**
 * Creator  liuqi
 * Data     2018/12/16
 * Class    com.yizhipin.base.ui.pop.TimeUtils
 * <p>时间工具类</p>
 */
object TimeUtils {
    fun showYMDialog(context: Context, listener: OnTimeSelectListener?, currentDate: Calendar) {
        TimePickerBuilder(context, listener)
                .setType(booleanArrayOf(true, true, false, false, false, false))
                .setDate(currentDate)
                .build()
                .show()
    }
}