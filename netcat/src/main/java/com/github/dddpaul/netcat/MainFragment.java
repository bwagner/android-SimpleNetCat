package com.github.dddpaul.netcat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainFragment extends Fragment
{
    private OnFragmentInteractionListener callback;

    @InjectView( R.id.et_connect_to)
    protected EditText connectToText;

    @InjectView( R.id.b_start )
    protected Button startButton;

    public static MainFragment newInstance()
    {
        return new MainFragment();
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        View view = inflater.inflate( R.layout.fragment_main, container, false );
        ButterKnife.inject( this, view );
        TextWatcher watcher = new TextWatcherAdapter()
        {
            public void afterTextChanged( final Editable editable )
            {
                updateUIWithValidation();
            }
        };
        connectToText.addTextChangedListener( watcher );
        startButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                callback.onFragmentInteraction( getResources().getInteger( R.integer.result_fragment_position ), connectToText.getText().toString() );
            }
        } );
        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        updateUIWithValidation();
    }

    @Override
    public void onAttach( Activity activity )
    {
        super.onAttach( activity );
        try {
            callback = (OnFragmentInteractionListener) activity;
        } catch( ClassCastException e ) {
            throw new ClassCastException( activity.toString() + " must implement OnFragmentInteractionListener" );
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        callback = null;
    }

    private void updateUIWithValidation()
    {
        boolean populated = Utils.populated( connectToText );
        startButton.setEnabled( populated );
    }
}
