package com.android.attendance.activity;

import com.android.attendance.bean.StudentBean;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddStudentActivity extends Activity {

	Button registerButton;
	EditText textFirstName;
	EditText textLastName;

	EditText textcontact;
	EditText textaddress;
	Spinner spinnerbranch,spinneryear;
	String userrole,branch,year;
	private String[] branchString = new String[] {"BS Computer Science", "Business Administration", "BS Media", "BS Maths", "BS Education",};
	private String[] yearString = new String[] {"2018","2019","2020","2021","2022"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addstudent);

		spinnerbranch=(Spinner)findViewById(R.id.spinnerdept);
		spinneryear=(Spinner)findViewById(R.id.spinneryear);
		textFirstName=(EditText)findViewById(R.id.editTextFirstName);
		textLastName=(EditText)findViewById(R.id.editTextLastName);
		textcontact=(EditText)findViewById(R.id.editTextPhone);
		textaddress=(EditText)findViewById(R.id.editTextaddr);
		registerButton=(Button)findViewById(R.id.RegisterButton);

		spinnerbranch.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				branch =(String) spinnerbranch.getSelectedItem();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, branchString);
		adapter_branch
		.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerbranch.setAdapter(adapter_branch);

		///......................spinner2

		spinneryear.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				year =(String) spinneryear.getSelectedItem();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, yearString);
		adapter_year
		.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinneryear.setAdapter(adapter_year);



		registerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//......................................validation
				String first_name = textFirstName.getText().toString();
				String last_name = textLastName.getText().toString();
				String phone_no = textcontact.getText().toString();
				String address = textaddress.getText().toString();

				if (TextUtils.isEmpty(first_name)) {
					textFirstName.setError("Please Enter First Name");
				}

				else if (TextUtils.isEmpty(last_name)) {
					textLastName.setError("Please Enter Last Name");
				}
				else if (TextUtils.isEmpty(phone_no)) {
					textcontact.setError("Please Enter Phone No.");
				}

				else if (TextUtils.isEmpty(address)) {
					textaddress.setError("Please Enter Address");
				}				
				else { 
					
					StudentBean studentBean = new StudentBean();
					
					studentBean.setStudent_firstname(first_name);
					studentBean.setStudent_lastname(last_name);
					studentBean.setStudent_mobilenumber(phone_no);
					studentBean.setStudent_address(address);
					studentBean.setStudent_department(branch);
					studentBean.setStudent_class(year);
					
					DBAdapter dbAdapter= new DBAdapter(AddStudentActivity.this);
					dbAdapter.addStudent(studentBean);
					
					Intent intent =new Intent(AddStudentActivity.this,MenuActivity.class);
					startActivity(intent);
					Toast.makeText(getApplicationContext(), "Student Added Successfully", Toast.LENGTH_SHORT).show();

				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
