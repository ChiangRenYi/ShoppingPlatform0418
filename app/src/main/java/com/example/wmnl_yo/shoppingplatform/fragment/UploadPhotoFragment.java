package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wmnl_yo.shoppingplatform.PhotoContent;
import com.example.wmnl_yo.shoppingplatform.PhotoContent.PhotoItem;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.object.PhotoObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WMNL-YO on 2017/4/18.
 */

public class UploadPhotoFragment extends Fragment{

    private List<PhotoObject> photoObjectList = new ArrayList<>();

    private View view;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    public static UploadPhotoRecyclerViewAdapter adapter;

    private FloatingActionButton fabAddPhoto;
    private String testlist[] ={"http://163.13.128.55/image/IMAG0026.jpg",
            "http://163.13.128.55/image/IMAG0027.jpg",
            "http://163.13.128.55/image/IMAG0028.jpg",
            "http://163.13.128.55/image/IMAG0029.jpg",
            "http://163.13.128.55/image/IMAG0030.jpg"};
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

            photoObjectList.add(new PhotoObject("http://163.13.128.55/image/IMAG0026.jpg", "2017/5/15", "老師講故事給小朋友聽"));
            photoObjectList.add(new PhotoObject("http://163.13.128.55/image/IMAG0027.jpg", "2017/5/15", "小朋友在玩推沙"));
            photoObjectList.add(new PhotoObject("http://163.13.128.55/image/IMAG0028.jpg", "2017/5/15", "小朋友們積極參與課上活動"));
            photoObjectList.add(new PhotoObject("http://163.13.128.55/image/IMAG0029.jpg", "2017/5/15", "外國交換學生互相學習"));
            photoObjectList.add(new PhotoObject("http://163.13.128.55/image/IMAG0030.jpg", "2017/5/15", "小朋友上音樂課"));

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_interactive_upload_photos_list, container, false);

        final Context context = view.getContext();

        recyclerView = (RecyclerView) view.findViewById(R.id.list_photo);
        fabAddPhoto = (FloatingActionButton)view.findViewById(R.id.fab_add_photos);

        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));

        adapter = new UploadPhotoRecyclerViewAdapter(PhotoContent.ITEMS, mListener);

        recyclerView.setAdapter(adapter);
//
//        PhotoContent.ITEMS.clear();

        PhotoContent dim = new PhotoContent();
        dim.ITEMS.clear();
        for(String i:testlist) {
            dim.addItem(new PhotoContent.PhotoItem(i));
        }

        adapter.notifyDataSetChanged();

        fabAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoContent dim = new PhotoContent();
                dim.addItem(new PhotoContent.PhotoItem("http://163.13.128.55/image/IMAG0026.jpg"));
                adapter.notifyDataSetChanged();

//                Fragment fragment = null;
//                Class fragmentClass = null;
//                fragmentClass = InteractivePhotosInfoFragment.class;
//                try {
//                    fragment = (Fragment) fragmentClass.newInstance();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                fragment.setTargetFragment(getTargetFragment(), 0);
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.flContent, fragment)
//                        .addToBackStack(null)
//                        .commit();
            }
        });

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
        void onListFragmentInteraction(PhotoItem item);
    }

    public static void removeItem(final RecyclerView.ViewHolder viewHolder){
        PhotoContent.ITEMS.remove(viewHolder.getAdapterPosition());
        adapter.notifyDataSetChanged();
    }

    public class UploadPhotoRecyclerViewAdapter extends RecyclerView.Adapter<UploadPhotoRecyclerViewAdapter.ViewHolder> {
        private final List<PhotoContent.PhotoItem> mValues;
        private final UploadPhotoFragment.OnListFragmentInteractionListener mListener;

        public UploadPhotoRecyclerViewAdapter(List<PhotoItem> items, UploadPhotoFragment.OnListFragmentInteractionListener listener) {
            mValues = items;
            mListener = listener;
        }

        @Override
        public UploadPhotoRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_interactive_photo, parent, false);
            return new UploadPhotoRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final UploadPhotoRecyclerViewAdapter.ViewHolder holder, final int position) {
            holder.mItem = mValues.get(position);
//            holder.mimg.setImageResource((mValues.get(position).content));

            Picasso.with(getActivity()).load(holder.mItem.content).into(holder.mimg);

            holder.mimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("photoDetail",photoObjectList.get(position));
                    PhotoDetailFragment fragobj = new PhotoDetailFragment();
                    fragobj.setArguments(bundle);
                    Log.d("55123",String.valueOf(position));
                    ((MainActivity) getContext()).replaceFragment(PhotoDetailFragment.class, fragobj);

//                    Toast.makeText(getActivity(),holder.mItem.content.toString(),Toast.LENGTH_SHORT).show();
//
//
//                    Fragment fragment = null;
//                    Class fragmentClass = null;
//                    fragmentClass = ChildInfoFragment.class;
//                    try {
//                        fragment = (Fragment) fragmentClass.newInstance();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    fragment.setTargetFragment(getTargetFragment(), 0);
//                    getActivity().getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.flContent, fragment)
//                            .addToBackStack(null)
//                            .commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public ImageView mimg;
            public PhotoContent.PhotoItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mimg = (ImageView) view.findViewById(R.id.ImageView_Upload_photos);
            }

//            @Override
//            public Int toInt() {
//                return super.toString() + " '" + mtitle.getText() + "'";
//            }
        }
    }
}
