package com.scrypt.sfax.home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.scrypt.sfax.R;
import com.scrypt.sfax.base.BaseApplication;
import com.scrypt.sfax.base.ViewModelFactory;
import com.scrypt.sfax.data.FaxNumber;
import com.scrypt.sfax.data.Folder;
import com.scrypt.sfax.screen.faxlist.FaxListFragment;
import com.scrypt.sfax.screen.faxlist.FaxListViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class AppEntryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

  @Inject ViewModelFactory viewModelFactory;
  private AppEntryViewModel viewModel;

  private Spinner numberSpinner;

  private ProgressBar progressBar;

  private Map<Integer,String> menuItemIds = null;

  private final int DYNAMIC_MENU_ID_BASE = 100;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // dependency injection
    BaseApplication.getApplicationComponent(getApplicationContext()).inject(this);

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppEntryViewModel.class);

    setContentView(R.layout.activity_app_entry);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    progressBar = (ProgressBar) findViewById(R.id.progressBar);
    progressBar.setVisibility(View.GONE);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_container);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    setupNavDrawer();

    observe();
  }

  private void setupNavDrawer() {

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    View hView =  navigationView.getHeaderView(0);
    numberSpinner = hView.findViewById(R.id.number_spinner);

    viewModel.getFaxNumbersLiveData().observe(this, numbers -> {
      if (numbers != null) {
        setupNumberSpinner(numbers);
      }
    });

    Menu menu = navigationView.getMenu();

    // get folder livedata acknowledged
    viewModel.getFoldersLiveData().observe(this, folders -> {
      if (folders != null) {
        int id = DYNAMIC_MENU_ID_BASE;

        menu.clear(); // remove all menu first

        if (menuItemIds != null) {
          menuItemIds.clear();
        } else {
          menuItemIds = new HashMap<>();
        }

        for(Folder folder : folders) {
          menuItemIds.put(id,folder.getId());
          MenuItem m = menu.add(Menu.NONE,id,Menu.NONE,folder.getName());
          m.setIcon(R.drawable.ic_add);
          id++;
        }
      }
    });

    /*
    SubMenu subMenu = menu.addSubMenu("WaWaWa");

    for (int i = 1; i <= 4; i++) {
      MenuItem m = subMenu.add("SubMenu Item " + i);
      m.setIcon(R.drawable.ic_add);
    }
    */

    viewModel.getHttpExceptionLiveData().observe(this, result -> {
      // handle http response exception
    });

  }

  private void observe() {

    viewModel.getProgressLiveData().observe(this, progress -> {
      if ((progress == 0) || (progress == 100)) {
        progressBar.setVisibility(View.GONE);
      } else if (progress > 0) {
        progressBar.setVisibility(View.VISIBLE);

        progressBar.setProgress(progress);
      }

    });


  }
  private void setupNumberSpinner(List<FaxNumber> numbers) {

    List<FaxNumber> list = new ArrayList<>();

    for(FaxNumber number : numbers) {
      list.add(number);  // FaxNumber toString() will be used for display in the spinner list
    }

    ArrayAdapter<FaxNumber> dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, list);

    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    if (numberSpinner != null) {
      numberSpinner.setAdapter(dataAdapter);

      numberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
          viewModel.callGetFolders(list.get(i).getId());
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
      });
    }
  }

  @Override
  public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
    return super.onCreateView(parent, name, context, attrs);
  }

  @Override
  protected void onResume() {
    super.onResume();

    // initial fragment launch
    viewModel.callGetFaxNumbers();
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {

    int id = item.getItemId();

    if ( id >= DYNAMIC_MENU_ID_BASE) {
      // fax number folder menus
      FaxListFragment f = new FaxListFragment();
      String menuId = menuItemIds.get(id);

      Bundle bundle = new Bundle();
      bundle.putString("id", menuId);
      f.setArguments(bundle);

      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
      ft.replace(R.id.nav_screen_container, f);
      ft.commit();

    } else {
      // static menus
      switchScreen(item.getItemId());
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_container);
    drawer.closeDrawer(GravityCompat.START);

    return true;
  }


  private void switchScreen(int itemId) {

    switch(itemId) {
      case R.id.nav_menu1:
        break;
    }


  }

}
