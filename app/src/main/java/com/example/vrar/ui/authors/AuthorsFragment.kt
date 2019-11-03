package com.example.vrar.ui.authors

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.TypedArrayUtils
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vrar.R
import com.example.vrar.ui.home.HomeViewModel

class AuthorsFragment : Fragment() {
    private lateinit var authorsViewModel: AuthorsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        authorsViewModel = ViewModelProviders.of(this).get(AuthorsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragmen_authors, container, false)


        var recyclerView :RecyclerView = root.findViewById(R.id.recycler_view)
        var authorAdapter : AuthorAdapter = AuthorAdapter(generateAuthors())
            recyclerView.adapter = authorAdapter
        var layoutManager : LinearLayoutManager = LinearLayoutManager(inflater.context)
        recyclerView.layoutManager = layoutManager

    return root
    }

    fun generateAuthors():List<Author>{
         return  listOf(
            Author( "Галерея «Простор»","Горькие",	"Алтайский край, г. Барнаул","Участники"),
            Author("Усадьба Саржевских","Озерники","Алтайский край, г. Барнаул","Победители"),
            Author("Нулевой километр",	"Альтаир"	,"Свердловская область, г.Екатеринбург","Победители"),
            Author("Памятник Николаю Рериху"	,"Открытый космос",	"Ростовская область, г. Ростов-на-Дону",	"Участники"),
            Author("Озеро «Бирюзовая Катунь»",	"Varapaparam"	,"Новгородская область, г. Великий Новгород","Победители"),
            Author("Чудеса природы и культуры",	"Команда Ы",	"Свердловская область, г. Первоуральск",	"Победители"),
            Author("Тавдинская пещера",	"ГеоНК",	"Республика Татарстан,г.Нижнекамск",	"Участники"),
            Author("Пещерный комплекс",	"ГеоНК",	"Республика Татарстан,г.Нижнекамск",	"Участники"),
            Author("Усадьба Саржевских",	"ГеоTeam",	"Алтайский край,г.Барнаул"	,"Победители"),
            Author("Обзор",	"Команда Ы",	"Свердловская область г.Первоуральск",	"Победители"))
    }
}

class AuthorAdapter(autors : List<Author>) : RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder>() {
    var autors : List<Author> = autors

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.authors_view, parent, false)
        return AuthorViewHolder(view)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        holder.bind(autors[position]
        )
    }

    override fun getItemCount(): Int {
        return autors.size
    }

    class AuthorViewHolder(v:View) : RecyclerView.ViewHolder(v) {

        var teamName : TextView = v.findViewById(R.id.name)
        var projectName : TextView = v.findViewById(R.id.project)
        var winner : TextView = v.findViewById(R.id.status)
        var from : TextView = v.findViewById(R.id.region)
        fun bind(author: Author){
                teamName.setText(author.team)
            projectName.setText(author.project)
            winner.setText(author.status)
            if (winner.text.toString() == "Победители")
                winner.setTextColor(Color.GREEN)
            else
                winner.setTextColor(Color.BLUE)
            from.setText(author.region)
        }

    }

}

class Author(project: String, team: String, region: String, status: String){
    var team = team
    var project = project
    var region = region
    var status = status
}

