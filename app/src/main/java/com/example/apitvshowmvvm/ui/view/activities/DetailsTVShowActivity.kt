package com.example.apitvshowmvvm.ui.view.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.*
import com.example.apitvshowmvvm.R
import com.example.apitvshowmvvm.core.adapter.EpisodesAdapter
import com.example.apitvshowmvvm.core.adapter.ImageAdapter
import com.example.apitvshowmvvm.data.database.TVShowsDatabase
import com.example.apitvshowmvvm.data.database.entities.TVShowEntity
import com.example.apitvshowmvvm.data.models.Episode
import com.example.apitvshowmvvm.data.models.TVShow
import com.example.apitvshowmvvm.data.repositories.TVShowRepository
import com.example.apitvshowmvvm.data.responses.TVShowDetailResponse
import com.example.apitvshowmvvm.databinding.ActivityDetailsTvshowBinding
import com.example.apitvshowmvvm.databinding.ActivityMainBinding


import com.example.apitvshowmvvm.ui.viewmodels.DetailsTVShowViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.text.DecimalFormat




class DetailsTVShowActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailsTvshowBinding
    private lateinit var bottomSheetDialog:BottomSheetDialog
    private val detailsTVShowViewModel:DetailsTVShowViewModel by viewModels()
    private var detailsTVShow:MutableList<TVShowDetailResponse> = mutableListOf()
    private var lstEpisodes:MutableList<Episode> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsTvshowBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Obtén el Intent que inició esta actividad y los extras
        val intent = intent
        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val startDate = intent.getStringExtra("startDate")
        val country = intent.getStringExtra("country")
        val network = intent.getStringExtra("network")
        val status = intent.getStringExtra("status")
        val tvShowJson= intent.getStringExtra("tvshow")

        //val tvShowRepository=TVShowRepository(TVShowsDatabase(binding.root.context))
        val gson = Gson()
        val viewPager: ViewPager2 =binding.sliderViewPager


        var imageList: ArrayList<String> = ArrayList()  // Puedes reemplazar esto con tu lógica para obtener las rutas de las imágenes

       // viewPager.adapter = imageAdapter
        //val imageAdapter = ImageAdapter(imageList)
        detailsTVShowViewModel.data.observe(this, Observer{

           TVShowDetailResponse->
            toggleLoading()
            Log.i("tagDetalle",TVShowDetailResponse.tvShows.pictures.toString() )

            //INCIALIZANDO LISTAS
            imageList.addAll(TVShowDetailResponse.tvShows.pictures)
            lstEpisodes.addAll(TVShowDetailResponse.tvShows.episodes)

            //Caragando Slider
            loadImageSlider(imageList)

            Picasso.get().load(TVShowDetailResponse.tvShows.image_path).into(binding.imageTVShow)
            binding.imageTVShow.visibility=View.VISIBLE
            binding.description=HtmlCompat.fromHtml(TVShowDetailResponse.tvShows.description,HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
            binding.tvDescription.visibility=View.VISIBLE
            binding.tvReadMore.visibility=View.VISIBLE


            // EVENT CLICK ON COMPONENTS
            binding.tvReadMore.setOnClickListener{
                if(binding.tvReadMore.text.toString().equals("Read More")){
                    binding.tvDescription.maxLines=Integer.MAX_VALUE
                    binding.tvDescription.ellipsize=null
                    binding.tvReadMore.text="Read Less"

                }else{
                    binding.tvDescription.maxLines=4
                    binding.tvDescription.ellipsize=TextUtils.TruncateAt.END
                    binding.tvReadMore.text="Read More"
                }
            }
            binding.buttonWebsite.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(TVShowDetailResponse.tvShows.url)
                startActivity(intent)

            }
            binding.imageWatchList.setOnClickListener{

                val tvShow = gson.fromJson(tvShowJson, TVShow::class.java)
                val tvShowEntity=TVShowEntity(tvShow.id,tvShow.name,tvShow.starDate,tvShow.country,tvShow.network,tvShow.status,tvShow.thumbnail)
              //  detailsTVShowViewModel.addToWatchList(tvShowEntity,tvShowRepository)
                binding.imageWatchList.setImageResource(R.drawable.ic_added)
                Toast.makeText(this,"tvshow saved",Toast.LENGTH_SHORT)

            }


            binding.rating=String.format("%.2f",TVShowDetailResponse.tvShows.rating)
            if(TVShowDetailResponse.tvShows.genres!=null){
                binding.genre=TVShowDetailResponse.tvShows.genres[0]
            }else{
                binding.genre="N/A"
            }


            binding.buttonWebsite.visibility=View.VISIBLE
            binding.buttonEpisodes.visibility=View.VISIBLE
            binding.runtime=TVShowDetailResponse.tvShows.runtime.toString()+" Min"
            binding.viewDivider1.visibility=View.VISIBLE
            binding.layoutMisc.visibility=View.VISIBLE
            binding.viewDivider2.visibility=View.VISIBLE
            binding.imageWatchList.visibility=View.VISIBLE
            loadBasicTVShowDetails()



        })



        id.let {
            getDetailsTVShow(id.toString())
            Toast.makeText(this,name + id.toString(),Toast.LENGTH_SHORT).show()
        }

        binding.buttonEpisodes.setOnClickListener {
            // Inflar el diseño del BottomSheetDialog
            val view = layoutInflater.inflate(R.layout.layout_episodes_bottom_sheet, null)

            // Crear el BottomSheetDialog
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(view)
            dialog.setCancelable(false)

            // Componentes
            val btnClose = view.findViewById<ImageView>(R.id.ivCloseEpisodes)
            val rvEpisodes=view.findViewById<RecyclerView>(R.id.rvEpisodes)


            val adapter=EpisodesAdapter(lstEpisodes)
            rvEpisodes.adapter=adapter


            btnClose.setOnClickListener {
                dialog.dismiss()
            }


            // Obtener el comportamiento del BottomSheetDialog
            val parent = view.parent as View
            val layoutParams = parent.layoutParams as CoordinatorLayout.LayoutParams
            val behavior = layoutParams.behavior as BottomSheetBehavior

            // Configurar la altura del dialog para que coincida con la altura de la pantalla
            behavior.peekHeight = ViewGroup.LayoutParams.MATCH_PARENT

            // Mostrar el BottomSheetDialog
            dialog.show()


        }

        binding.imageBack.setOnClickListener{
            finish()
        }


    }


    fun getDetailsTVShow(id:String){
            toggleLoading()
            detailsTVShowViewModel.getDetails(id)
    }

    fun toggleLoading(){
        binding.isLoading = binding.isLoading != true

    }

    fun loadImageSlider(listImages:ArrayList<String>){

        binding.sliderViewPager.offscreenPageLimit=1
        val imageAdapter = ImageAdapter(listImages)
        binding.sliderViewPager.adapter=imageAdapter
        binding.sliderViewPager.visibility=View.VISIBLE
        binding.viewFadingEdge.visibility= View.VISIBLE

        setupSliderIndicator(listImages.size)
        binding.sliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setCurrentSliderIndicator(position)
            }

            })
    }

    fun setupSliderIndicator(cantidad:Int){
        val indicator=ArrayList<ImageView>(cantidad)
        val linearLayoutParams= LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)

        linearLayoutParams.setMargins(8,0,8,0)

        for (i in 0 until cantidad) {
            val element = ImageView(this)
            indicator.add(element)
        }
        for(element in indicator){

            element.setImageDrawable(ContextCompat.getDrawable(this , R.drawable.background_slider_indicator_inactive))
            element.layoutParams=linearLayoutParams
            binding.layoutSliderIndicators.addView(element)
        }

        setCurrentSliderIndicator(0)
        binding.layoutSliderIndicators.visibility=View.VISIBLE

    }
    fun setCurrentSliderIndicator(position: Int){
        val childCount=binding.layoutSliderIndicators.childCount
        for(i in 0 until childCount){
            val imageView=binding.layoutSliderIndicators.getChildAt(i) as ImageView
            if(i==position){
                imageView.setImageDrawable(ContextCompat.getDrawable(this , R.drawable.background_slider_indicator_active))

            }else{
                imageView.setImageDrawable(ContextCompat.getDrawable(this , R.drawable.background_slider_indicator_inactive))

            }

        }
    }

    fun loadBasicTVShowDetails(){
        binding.tvShowName=intent.getStringExtra("name")
        binding.networkCountry=intent.getStringExtra("network") +"(" +intent.getStringExtra("country")+")"
        binding.startedDate=intent.getStringExtra("startDate")
        binding.status=intent.getStringExtra("status")
        binding.startedDate=intent.getStringExtra("startDate")

        binding.tvNameTVShow.visibility=View.VISIBLE
        binding.tvNetWorkCountry.visibility=View.VISIBLE
        binding.tvStatus.visibility=View.VISIBLE
        binding.tvStartedTVShow.visibility=View.VISIBLE



    }




}
