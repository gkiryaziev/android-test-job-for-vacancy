package android.ua.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.ua.testapp.dbmanager.DBHelper;
import android.ua.testapp.dbmanager.models.Record;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class FMain extends Activity implements OnClickListener {

    // components
    LinearLayout frmMain;
    Button btnChangeBgColor, btnLoadData, btnTruncateData;
    TextView txtResult;
    ListView lstViewRecords;

    //database
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fmain);

        frmMain = (LinearLayout)findViewById(R.id.frmMain);
        txtResult = (TextView)findViewById(R.id.txtResult);
        lstViewRecords = (ListView)findViewById(R.id.lstViewRecords);

        btnChangeBgColor = (Button)findViewById(R.id.btnChangeBgColor);
        btnLoadData = (Button)findViewById(R.id.btnLoadData);
        btnTruncateData = (Button)findViewById(R.id.btnTruncateData);

        btnChangeBgColor.setOnClickListener(this);
        btnLoadData.setOnClickListener(this);
        btnTruncateData.setOnClickListener(this);

        dbHelper = new DBHelper(this);
        fillListView();
    }

    public void fillListView() {
        List<Record> recordList = dbHelper.getRecords();
        ListAdapter listAdapter = new CustomAdapter(this, recordList);
        lstViewRecords.setAdapter(listAdapter);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnChangeBgColor:
                frmMain.setBackgroundColor(ColorChanger.getRandomColor());
                break;
            case R.id.btnLoadData:
                try {
                    String result = new AsyncTestDataLoader().execute("http://rsrv-devmaster.rhcloud.com/test").get();
                    txtResult.setText(DateParser.unixToFormatedString("dd.MM.yyyy HH:mm:ss", Long.parseLong(result)));
                    dbHelper.addRecord(new Record(result));
                    fillListView();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnTruncateData:
                dbHelper.truncateDatabase();
                fillListView();
                break;
            default: break;
        }
    }
}
