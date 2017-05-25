# android-mvp
使用kotlin实现简单的Android MVP模式Demo

### 另外一个项目 [android-mvp-kotlin](https://github.com/mazouri/android-mvp-kotlin)使用了Dagger2、Retrofit、RxJava等来实现MVP


<img src="/screenshot/android-mvp.gif"/>


**谷歌宣布，将Kotlin语言作为安卓开发的一级编程语言**
[Google I/O 大会全程视频直播](https://v.qq.com/x/page/f0504oafzh9.html)

为什么要学习Kotlin?因为它能使Android的开发更简洁、高效及安全，更因为谷歌的推崇！

不说废话，直入主题。很久之前在看mvp模式的时候，看多很多小例子，这里用kotlin来简单实现一下，完全是入门级的，对于刚刚了解kotlin来开发Android的同学，是个不错的例子。

完整案例和使用Dagger2、Retrofit、RxJava、Kotlin实现MVP的源码欢迎Github查看：

[kotlin简单实现MVP之android-mvp源码](https://github.com/mazouri/android-mvp)

[Kotlin结合Dagger2等开源项目实现MVP之android-mvp-kotlin源码](https://github.com/mazouri/android-mvp-kotlin)

案例主要功能是：用户输入用户id、姓名、年龄等信息进行保存，然后通过用户id读取保存的信息。案例很简单，但是涵盖了mvp的各个要素。


**MVP: Presenter与Model、View都是通过接口来进行交互的，从而来降低耦合**

１．首先，创建一个数据类：
(关于kotlin知识点：1.怎么创建数据类，2.类构造方法)
```
/**
 * 创建一个数据类
 *
 * Kotlin知识点(data class)：http://kotlinlang.org/docs/reference/data-classes.html#data-classes
 *
 * Created by wangdongdong on 17-5-23.
 */
data class User(val userName: String, val age: Int)
```
２．创建View接口：
(关于kotlin知识点：1.怎么创建一个接口，2.接口方法写法，3.返回值类型，4.方法的参数)
```
interface IUserView {

    fun getID(): Int
    fun getUsername(): String
    fun getAge(): Int
    fun setUsername(username: String)
    fun setAge(age: Int)
    fun onSaveSuccess()
}
```
３．创建Model接口
```
interface IUserModel {
    fun setId(id: Int)
    fun setUsername(username: String)
    fun setAge(age: Int)
    fun save()
    fun load(id: Int): User
}
```
４．创建Model实现类：
(关于kotlin知识点：1.实现接口的类，2.方法覆写)
```
class UserModel: IUserModel {

    private var mId: Int = 0
    private var mUsername: String = ""
    private var mAge: Int = 0
    private val mUserArray = SparseArray<User>()

    override fun setId(id: Int) {
        mId = id
    }

    override fun setUsername(username: String) {
        mUsername = username
    }

    override fun setAge(age: Int) {
        mAge = age
    }

    override fun save() {
        mUserArray.append(mId, User(mUsername, mAge))
    }

    override fun load(id: Int): User {
        mId = id
        return mUserArray.get(mId, User("not found", 0))
    }
}
```
5．创建Presenter接口：

```
interface IUserPresenter {
    fun saveUser(id: Int, username: String, age: Int)
    fun loadUser(id: Int)
}
```
6．创建Presenter实现类：

```
class UserPresenter(val view: IUserView): IUserPresenter {

    private val mUserModel: UserModel = UserModel()

    override fun saveUser(id: Int, username: String, age: Int) {
        Log.d("test_log", "saveUser : $id,$username,$age")
        mUserModel.setId(id)
        mUserModel.setUsername(username)
        mUserModel.setAge(age)
        mUserModel.save()
        view.onSaveSuccess()
    }

    override fun loadUser(id: Int) {
        Log.d("test_log", "loadUser : $id")
        val user = mUserModel.load(id)
        view.setUsername(user.userName)
        view.setAge(user.age)
    }
}
```
７．创建Activity:

```
class MainActivity : AppCompatActivity(), IUserView, View.OnClickListener {

    private var mUserPresenter: UserPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUserPresenter = UserPresenter(this)
        saveButton.setOnClickListener(this)
        loadButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.saveButton -> mUserPresenter?.saveUser(getID(), getUsername(), getAge())
            R.id.loadButton -> mUserPresenter?.loadUser(getID())
        }
    }

    override fun onSaveSuccess() {
        edt_id.setText("")
        edt_username.setText("")
        edt_age.setText("")
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show()
    }

    override fun getID(): Int {
        val id = edt_id.text.toString().trim()
        if (id.isNotEmpty())
            return id.toInt()
        else
            return 0
    }

    override fun getUsername(): String = edt_username.text.toString()

    override fun getAge(): Int {
        val age = edt_age.text.toString().trim()
        if (age.isNotEmpty())
            return age.toInt()
        else
            return 0
    }

    override fun setUsername(username: String) {
        Log.d("test_log", "setUsername:$username")
        edt_username.text = toEditable(username)
    }

    override fun setAge(age: Int) {
        Log.d("test_log", "setUsername:$age")
        edt_age.text = toEditable(age.toString())
    }
}
```
结构很简洁，代码也很简单，相信看完代码后，大家对kotlin的基本使用和mvp模式都能有所了解了！
