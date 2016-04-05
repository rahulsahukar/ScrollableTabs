package scrollabletabs.rahul.com.scrollabletabs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hadoop on 22/3/16.
 */
public class FragmentOne extends Fragment {

    String[] str = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",};

    private RecyclerView rv;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentone, container, false);

        rv = (RecyclerView) view.findViewById(R.id.recycle);
        tabLayout = (TabLayout)getActivity().findViewById(R.id.tabs);

        MyRecyclerAdapter adapter = new MyRecyclerAdapter(getActivity());
        rv.setAdapter(adapter);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

/*
        rv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                View firstChildInList = rv.getChildAt(0);
                if (firstChildInList == null) return;
                rv.setTranslationY(firstChildInList.getTop() + firstChildInList.getHeight());
            }
        });
*/

        return view;
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);
    }

    @Override
    public void onResume() {
        super.onResume();

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int[] loc = new int[2];
                tabLayout.getLocationInWindow(loc);
                float ty = loc[1] + tabLayout.getHeight();
                float ty_y = loc[1];

                rv.getLocationInWindow(loc);
                float ry = loc[1];
                if(dy>0) {
                    if(ry>=ty_y){
                        float translate = ((ry-dy)<=ty_y?(ry-ty_y):dy);
                        rv.setTranslationY(-translate);
                    }
                }
                else if( dy<0 ){
                    if(ry<=ty_y) {
                        float translate = ((ry - dy) >= ty ? (ty-ry) : dy);
                        rv.setTranslationY(translate);
                    }
                }

                rv.getLocationInWindow(loc);
                Log.i("Position ", "" + loc[1]);
                if (ry < ty) {
                    //rv.setTranslationY(ry - ty);
                }
                float c = ty + ry;

            }
        });
    }

    public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {
        private Context mContext;

        public MyRecyclerAdapter(Context context) {
            this.mContext = context;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {
            protected TextView textView;

            public CustomViewHolder(View view) {
                super(view);
                this.textView = (TextView) view.findViewById(R.id.textview);
            }
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, null);

            CustomViewHolder viewHolder = new CustomViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
            customViewHolder.textView.setText(str[i]);
        }

        @Override
        public int getItemCount() {
            return str.length;
        }
    }
}
