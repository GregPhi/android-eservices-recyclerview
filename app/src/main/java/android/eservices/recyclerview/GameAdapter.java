package android.eservices.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder>{
    private List<GameViewModel> gameViewModels;
    private static GameActionInterface gameActionInterface;

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(parent.getContext() instanceof GameActionInterface){
            gameActionInterface = (GameActionInterface) parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,parent,false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        holder.bind(gameViewModels.get(position));
    }

    @Override
    public int getItemCount() {
        if (gameViewModels != null)
            return gameViewModels.size();
        else return 0;
    }

    public void bindGameViewModelList(List<GameViewModel> gameViewModels){
        this.gameViewModels = gameViewModels;
        notifyDataSetChanged();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder{
        private TextView gameTitle;
        private TextView gameDescription;
        private ImageView gameImage;
        private ImageButton gameImageButtonInfo;
        private ImageButton gameImageButtonImg;
        private View view;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            gameTitle = itemView.findViewById(R.id.title_textview);
            gameDescription = itemView.findViewById(R.id.description_textview);
            gameImage = itemView.findViewById(R.id.icon_imageview);
            gameImageButtonInfo = itemView.findViewById(R.id.info_button);
            gameImageButtonImg = itemView.findViewById(R.id.game_button);
        }

        public void bind(final GameViewModel gameViewModel){
            gameTitle.setText(gameViewModel.getTitle());
            gameDescription.setText(gameViewModel.getDescription());
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);
            Glide.with(view).load(gameViewModel.getImageUrl()).apply(options).into(gameImage);
            gameImageButtonInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gameActionInterface.onGameInfoClicked(gameViewModel.getTitle());
                }
            });
            gameImageButtonImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gameActionInterface.onGameClicked(gameViewModel.getTitle());
                }
            });
        }
    }
}
