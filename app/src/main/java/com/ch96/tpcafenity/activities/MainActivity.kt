package com.ch96.tpcafenity.activities

//import com.ch96.tpcafenity.fragments.TabListFragment
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ch96.tpcafenity.GV
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.databinding.ActivityMainBinding
import com.ch96.tpcafenity.fragments.AccountFragment
import com.ch96.tpcafenity.fragments.CommunityFragment
import com.ch96.tpcafenity.fragments.HomeFragment
import com.ch96.tpcafenity.fragments.InterestsFragment
import com.ch96.tpcafenity.fragments.TabListFragment
import com.ch96.tpcafenity.model.KakaoSearchPlaceResponse
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val TAG_HOME = "home_fragment"
private const val TAG_INTERESTS = "interests_fragment"
private const val TAG_COMMUNITY = "community_fragment"
private const val TAG_ACCOUNT = "account_fragment"

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    //검색응답 결과객체 참조변수
    var searchPlaceResponse:KakaoSearchPlaceResponse ?= null

    //Kakao Map 요청 데이터 - category_group_code(카테고리 그룹 코드), x(경도:longitude), y(위도:latitude)
    //1. 카테고리 그룹 코드
    var categoryCode:String = "CE7" //카페
    //2. 현재 내위치 정보 객체 (위도 : y, 경도 : x)
    var myLocation: Location? = null

    //Google Fused Location API (통합 위치 제공자)
    val locationProvider: FusedLocationProviderClient by lazy { LocationServices.getFusedLocationProviderClient(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //툴바 설정
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setFragment(TAG_HOME, HomeFragment())

        binding.naviBottom.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeFragment -> setFragment(TAG_HOME, HomeFragment())
                R.id.interestsFragment -> setFragment(TAG_INTERESTS, InterestsFragment())
                R.id.communityFragment -> setFragment(TAG_COMMUNITY, CommunityFragment())
                R.id.accountFragment -> setFragment(TAG_ACCOUNT, AccountFragment())
            }
            true
        }

        //위치정보 제공 동적퍼미션 (PermissionDenied -> launch permission, PermissionGranted -> request Location)
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED)
            permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        else if (checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED)
            permissionLauncher.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        else requestMyLocation()

    }

    //퍼미션 결과 받아오기 (ResultLauncher - register(1.contracts(about permission), 2.result callback(O/X))
    val permissionLauncher: ActivityResultLauncher<String> = registerForActivityResult(ActivityResultContracts.RequestPermission(),
            object: ActivityResultCallback<Boolean> {
                override fun onActivityResult(result: Boolean?) {
                    if (result!!) requestMyLocation() //위치요청
                    else Toast.makeText(this@MainActivity, "위치정보제공을 거부합니다. \n카페검색이 제한됩니다.", Toast.LENGTH_SHORT).show()
                }
            }
        )

    //위치 요청
    private fun requestMyLocation() {

        //위치 검색기준 설정하는 요청 객체
        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()

        //실시간 위치정보 갱신 요청 (퍼미션 받았는지 확인)
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) return

        locationProvider.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
    }

    //위치 검색 콜백 객체
    private val locationCallback = object:LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            super.onLocationResult(p0)

            myLocation = p0.lastLocation

            //위치 탐색 완료, 실시간 업데이트 종료
            locationProvider.removeLocationUpdates(this)
            //얻어온 위치정보를 통해 검색 시작
            searchPlace()

            Log.i("what_location", "$myLocation")
        }
    }

    //Kakao 장소 검색 API 파싱 메소드
    private fun searchPlace() {

        //REST API
        val retrofit = RetrofitHelper.getRetrofitInstance(GV.kakaoUrl)
        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.searchPlace(categoryCode, myLocation?.latitude.toString(), myLocation?.longitude.toString())
            .enqueue(object:Callback<KakaoSearchPlaceResponse>{
                override fun onResponse(
                    call: Call<KakaoSearchPlaceResponse>, response: Response<KakaoSearchPlaceResponse>
                ) {
                    searchPlaceResponse = response.body()
                    supportFragmentManager.beginTransaction().replace(R.id.container_fragment, TabListFragment()).commit()
                    var searchWord = intent.getStringExtra("searchWord")

                }

                override fun onFailure(call: Call<KakaoSearchPlaceResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "데이터를 불러오지 못했습니다.", Toast.LENGTH_SHORT).show()
                }

            })
    }

    //toolbar - when clicked menu item
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.menu_search -> {
                startActivity(Intent(this,SearchActivity::class.java))
            }
            R.id.menu_noti -> {
                binding.drawerLayout.openDrawer(binding.navDrawer)
                return true
            }
        }
        false
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() { //뒤로가기 했을 때
        if (binding.drawerLayout.isDrawerOpen(binding.navDrawer)) {
            binding.drawerLayout.closeDrawer(binding.navDrawer)
        } else {
            super.onBackPressed()
        }
    }


    //Bottom navigation - fragment
    private fun setFragment(tag:String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if(manager.findFragmentByTag(tag) == null) {
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        val home = manager.findFragmentByTag(TAG_HOME)
        val intersts = manager.findFragmentByTag(TAG_INTERESTS)
        val community = manager.findFragmentByTag(TAG_COMMUNITY)
        val account = manager.findFragmentByTag(TAG_ACCOUNT)

        if (home != null) {
            fragTransaction.hide(home)
        }
        if (intersts != null) {
            fragTransaction.hide(intersts)
        }
        if (community != null) {
            fragTransaction.hide(community)
        }
        if (account != null) {
            fragTransaction.hide(account)
        }

        if (tag == TAG_HOME) {
            if (home != null) {
                fragTransaction.show(home)
            }
        } else if (tag == TAG_INTERESTS) {
            if (intersts != null) {
                fragTransaction.show(intersts)
            }
        } else if (tag == TAG_COMMUNITY) {
            if (community != null) {
                fragTransaction.show(community)
            }
        } else if (tag == TAG_ACCOUNT) {
            if (account != null) {
                fragTransaction.show(account)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }
}