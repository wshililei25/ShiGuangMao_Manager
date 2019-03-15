package com.yizhipin.base.ui.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.PermissionChecker
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yizhipin.base.R
import com.yizhipin.base.common.AppManager
import org.jetbrains.anko.find
import java.util.ArrayList
import kotlin.collections.HashMap

/**
 * Created by ${XiLei} on 2018/5/28.
 */
open class BaseActivity : RxAppCompatActivity() {

    /**
     * 权限集合，权限名-需要的权限说明
     */
    private val permissionMap = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ARouter.getInstance().inject(this)
        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

    //获取Window中视图content
    val contentView: View
        get() {
            val content = find<FrameLayout>(android.R.id.content)
            return content.getChildAt(0)
        }

    // 启动应用的设置
    open fun startAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }

    /**
     * 校验权限
     * @param permissions 权限集合，权限名-需要的权限说明
     * @param requestCode 权限请求
     */
    fun checkPermission(permissions: Map<String, String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.permissionMap.clear()
            this.permissionMap.putAll(permissions)
            val deniedPermissions = ArrayList<String>()//被拒绝的权限
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(this, permission.key) == PermissionChecker.PERMISSION_DENIED) {
                    deniedPermissions.add(permission.key)
                }
            }
            if (deniedPermissions.size == 0) return
            //如果被拒绝的权限不为空，则请求权限
            val array = arrayOfNulls<String>(deniedPermissions.size)
            deniedPermissions.forEachIndexed { index, s ->
                array[index] = s
            }
            ActivityCompat.requestPermissions(this, array, requestCode)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissions.forEachIndexed { index, s ->
            if (grantResults[index] == PermissionChecker.PERMISSION_DENIED) {
                AlertDialog.Builder(this).setMessage(this.permissionMap[s]).setPositiveButton(R.string.confirm) { _, _ -> startAppSettings() }.setNegativeButton(R.string.cancel, null).show()
            }
        }
    }

    fun hideSoftInput(){
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(window.decorView.windowToken,0)
    }
}