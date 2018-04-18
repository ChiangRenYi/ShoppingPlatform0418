package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wmnl_yo.shoppingplatform.KinshipContent;
import com.example.wmnl_yo.shoppingplatform.KinshipContent.KinshipItem;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.R;

import java.util.List;

/**
 * Created by WMNL-YO on 2017/3/30.
 */

public class KinshipManageFragment extends Fragment{

    private View view;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    public static KinshipManageRecyclerViewAdapter adapter;

    private FloatingActionButton fabAddChild;

    // TODO: Rename and change types and number of parameters
    public static KinshipManageFragment newInstance(int columnCount) {
        KinshipManageFragment fragment = new KinshipManageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_kinship, container, false);

        final Context context = view.getContext();

        recyclerView = (RecyclerView) view.findViewById(R.id.list_kinship_child);
        fabAddChild = (FloatingActionButton)view.findViewById(R.id.fab_add_child);

        recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));

        adapter = new KinshipManageRecyclerViewAdapter(KinshipContent.ITEMS, mListener);

        recyclerView.setAdapter(adapter);
//        KinshipContent.ITEMS.clear();
//        KinshipContent dim = new KinshipContent();
//        dim.addItem(new KinshipContent.KinshipItem("Child1"));
//        dim.addItem(new KinshipContent.KinshipItem("Child2"));
//        dim.addItem(new KinshipContent.KinshipItem("Child3"));

        adapter.notifyDataSetChanged();

        fabAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                Class fragmentClass = null;
                fragmentClass = ChildInfoFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                fragment.setTargetFragment(getTargetFragment(), 0);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flContent, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        ItemTouchHelper simpleCallback = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT, ItemTouchHelper.RIGHT) {


            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                new AlertDialog.Builder(getActivity()).setMessage("Delete").setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        KinshipContent.ITEMS.remove(viewHolder.getAdapterPosition());
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyDataSetChanged();
                    }
                }).show();
            }
        });
        simpleCallback.attachToRecyclerView(recyclerView);

        return view;

    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    //((MainActivity) getActivity()).getGroupInfo();
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(KinshipItem item);
    }

    public static void removeItem(final RecyclerView.ViewHolder viewHolder){
        KinshipContent.ITEMS.remove(viewHolder.getAdapterPosition());
        adapter.notifyDataSetChanged();
    }

    public class KinshipManageRecyclerViewAdapter extends RecyclerView.Adapter<KinshipManageRecyclerViewAdapter.ViewHolder> {
        private final List<KinshipContent.KinshipItem> mValues;
        private final KinshipManageFragment.OnListFragmentInteractionListener mListener;

        public KinshipManageRecyclerViewAdapter(List<KinshipItem> items, KinshipManageFragment.OnListFragmentInteractionListener listener) {
            mValues = items;
            mListener = listener;
        }

        @Override
        public KinshipManageRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_child_kinship, parent, false);
            return new KinshipManageRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final KinshipManageRecyclerViewAdapter.ViewHolder holder, final int position) {
            holder.mItem = mValues.get(position);
            holder.mtitle.setText((mValues.get(position).title));

            holder.mtitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChildInfoFragment ChildInfofrg = new ChildInfoFragment();
                    ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flContent, ChildInfofrg).addToBackStack(null).commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mtitle;
            public KinshipContent.KinshipItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mtitle = (TextView) view.findViewById(R.id.textView_kinship_child_items);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mtitle.getText() + "'";
            }
        }
    }

}
