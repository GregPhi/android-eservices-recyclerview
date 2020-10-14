package android.eservices.recyclerview;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity implements GameActionInterface{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private GameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        setupRecyclerView();


    }

    private void setupRecyclerView() {
        //TODO Bind recyclerview and set its adapter.
        recyclerView = findViewById(R.id.my_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new GameAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        //Use data generator to get data to display.
        List<GameViewModel> gameViewModels = DataGenerator.generateData();
        adapter.bindGameViewModelList(gameViewModels);
    }

    public void displaySnackBar(String message) {
        //TODO write a method that displays a snackbar in the coordinator layout with the "message" parameter as content.
        Snackbar snackbar = Snackbar.make(coordinatorLayout,message,Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onGameInfoClicked(String gameTitle) {
        List<GameViewModel> gameViewModels = DataGenerator.generateData();
        for(GameViewModel g : gameViewModels){
            if(gameTitle.equals(g.getTitle())){
                displaySnackBar("Jeu : "+g.getDescription());
            }
        }
    }

    @Override
    public void onGameClicked(String gameTitle) {
        List<GameViewModel> gameViewModels = DataGenerator.generateData();
        for(GameViewModel g : gameViewModels){
            if(gameTitle.equals(g.getTitle())){
                displaySnackBar(g.getDescription()+" si ma mémoire est bonne");
            }
        }
    }

    //TODO create callback methods for item click
    //Use ressource strings to get the text to display

}
