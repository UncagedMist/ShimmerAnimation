package kk.techbytecare.shimmeranimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.supercharge.shimmerlayout.ShimmerLayout;
import kk.techbytecare.shimmeranimation.Adapter.MyAdapter;
import kk.techbytecare.shimmeranimation.Model.Model;
import kk.techbytecare.shimmeranimation.Retrofit.IMyAPI;
import kk.techbytecare.shimmeranimation.Retrofit.RetrofitClient;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    IMyAPI myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ShimmerLayout shimmerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(IMyAPI.class);

        shimmerLayout = findViewById(R.id.shimmerLayout);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    private void fetchData() {
        shimmerLayout.startShimmerAnimation();

        compositeDisposable.add(myAPI.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Model>>() {
                    @Override
                    public void accept(List<Model> models) throws Exception {
                        displayData(models);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    private void displayData(List<Model> models) {
        MyAdapter adapter = new MyAdapter(this,models);
        recyclerView.setAdapter(adapter);
        shimmerLayout.stopShimmerAnimation();
        shimmerLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
