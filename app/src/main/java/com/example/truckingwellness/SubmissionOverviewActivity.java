package com.example.truckingwellness;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class SubmissionOverviewActivity extends AppCompatActivity {

    Button submit ,endNo , endYes ;

    int number;
    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> mobileCollection;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    TextView clientName;

    RadioButton flag_yes , flag_no ;
    FirebaseFirestore db;

    RelativeLayout flag_container;

    TextView medication , flag_reason_text , diasResult, sysResult,bloodSugarResult, cholesterolResult, waistResult,bmiResult , diasBloodStatus, sysBloodStatus,bloodSugarStatus,cholesterolStatus,waistStatus,bmiStatus , bloodSugarStatusRetest, bloodSugarResultRetest,diasBloodStatusRetest,diasBloodResultStatus,sysBloodStatusReset,sysBloodResultRetest, hiv_result , hiv_confirm_result ;

    final Random myRandom = new Random();



    int appointNum = 0;

    Double finalNumber;

    public String flag = "No", medication_given = "--" , flag_reason = "--" , hivStatus;

    Dialog dialog;

    Date fulldate = Calendar.getInstance().getTime();
    String setdateTime = String.valueOf((Date)fulldate);

    Date curDate = Calendar.getInstance().getTime();
    String dateTime = DateFormat.getDateInstance(DateFormat.FULL).format(curDate);
    String[] splitDate = dateTime.split(",");
    Calendar cal=Calendar.getInstance();
    SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
    String month_name = month_date.format(cal.getTime());
    Date curDate2 = Calendar.getInstance().getTime();
    Date d=new Date();
    SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy, h:mm a");
    String dateTime2 = sdf.format(d);

    UUID randomID = UUID.randomUUID();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_26);

        SharedPreferences flag_details = getApplication().getSharedPreferences("flagged_info", Context.MODE_PRIVATE);
        String flagged_details = flag_details.getString("flagged_info", "");

        SharedPreferences flag_details_reason = getApplication().getSharedPreferences("flagged_info_reason", Context.MODE_PRIVATE);
        String flagged_details_reasons = flag_details_reason.getString("flagged_info_reason", "");


        dialog = new Dialog(SubmissionOverviewActivity.this);
        dialog.setContentView(R.layout.exit_noti);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        endNo = dialog.findViewById(R.id.endNo);
        endYes = dialog.findViewById(R.id.endYes);

        finalNumber = Double.valueOf(Math.round(Wellness5Activity.Bmi));
        diasResult = findViewById(R.id.diasBloodResult);
        diasBloodStatus = findViewById(R.id.diasBloodStatus);
        sysResult = findViewById(R.id.sysBloodResult);
        sysBloodStatus = findViewById(R.id.sysBloodStatus);
        bloodSugarStatus = findViewById(R.id.bloodSugarStatus);
        bloodSugarResult = findViewById(R.id.bloodSugarResult);
        cholesterolResult = findViewById(R.id.cholesterolResult);
        cholesterolStatus = findViewById(R.id.cholesterolStatus);
        waistResult= findViewById(R.id.waistCircumResult);
        waistStatus = findViewById(R.id.waistCircumStatus);
        bmiResult = findViewById(R.id.bmiResult);
        bmiStatus = findViewById(R.id.bmiStatus);
        sysBloodResultRetest = findViewById(R.id.sysBloodResultRetest);
        sysBloodStatusReset = findViewById(R.id.sysBloodStatusRetest);
        diasBloodResultStatus = findViewById(R.id.diasBloodResultRetest);
        diasBloodStatusRetest = findViewById(R.id.diasBloodStatusRetest);
        bloodSugarResultRetest = findViewById(R.id.bloodSugarResultRetest);
        bloodSugarStatusRetest = findViewById(R.id.bloodSugarStatusRetest);
        hiv_result = findViewById(R.id.hiv_result);
        hiv_confirm_result = findViewById(R.id.hiv_result_restest);

        hiv_result.setText(TestKitResultsActivity.result);
        if (TestKitResultsActivity.result.equals("Reactive")){
            hiv_confirm_result.setText(ConfirmationResultActivity.confrimResult);
        }

        if (Wellness4Activity.measurement.equals("Yes")){
        diasResult.setText(String.valueOf(Wellness4Activity.bpUp));
        diasBloodStatus.setText(Wellness4Activity.bloodPressureStatus);
        sysBloodStatus.setText(Wellness4Activity.bloodPressureStatus);
        sysResult.setText(String.valueOf(Wellness4Activity.bpLow));
        bloodSugarResult.setText(String.valueOf(Wellness5Activity.sugar));
        bloodSugarStatus.setText(Wellness5Activity.sugarStatus);
        cholesterolStatus.setText(Wellness5Activity.cholStatus);
        cholesterolResult.setText(String.valueOf(Wellness5Activity.cholesterol));
        waistResult.setText(String.valueOf(Wellness5Activity.waist));
        waistResult.setText(String.valueOf(Wellness5Activity.waist));
        bmiResult.setText(String.valueOf(finalNumber));
        bmiStatus.setText(Wellness5Activity.bmiStatus);
        }

        if (Wellness4Activity.bloodPressureStatus == "Low Blood Pressure" || Wellness4Activity.bloodPressureStatus == "Dangerously high"){
            sysBloodStatusReset.setText(Wellness_retest2.bloodPressureStatus_retest);
            sysBloodResultRetest.setText(String.valueOf(Wellness_retest2.bp_up_retest));
            diasBloodResultStatus.setText(String.valueOf(Wellness_retest2.bp_low_retest));
        }else if (Wellness5Activity.sugarStatus == "Dangerously Low" || Wellness5Activity.sugarStatus == "Dangerously High"){
            bloodSugarStatusRetest.setText(Wellness_retest3.sugarStatus_retest);
            bloodSugarResultRetest.setText(String.valueOf(Wellness_retest3.sugar_retest));
        }else if((Wellness4Activity.bloodPressureStatus == "Low Blood Pressure" || Wellness4Activity.bloodPressureStatus == "Dangerously high") && (Wellness5Activity.sugarStatus == "Dangerously Low" || Wellness5Activity.sugarStatus == "Dangerously high")) {
            sysBloodStatusReset.setText(Wellness_restest.bloodPressureStatus_retest);
            sysBloodResultRetest.setText(String.valueOf(Wellness_restest.bp_up_retest));
            diasBloodResultStatus.setText(String.valueOf(Wellness_restest.bp_low_retest));
            bloodSugarStatusRetest.setText(Wellness_restest.sugarStatus_retest);
            bloodSugarResultRetest.setText(String.valueOf(Wellness_restest.sugar_retest));
        }

        endYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClientDetailsActivity.name = "";
                ClientDetailsActivity.surname = "";
                ClientDetailsActivity.companyName = "";
                TestTypeActivity.idType = "";
                TestTypeActivity.idNo = "";
                ClientDetailsActivity.cellNum = "";
                ClientDetailsActivity.optionalNum = "";
                DemographicsActivity.membershipNum = "";
                TestingReasonActivity.testReason = "";
                TestingReasonActivity.otherReasonTest = "";
                TestKitDetailsActivity.testKitNum = "";
                ConfirmationTestActivity.confirmTestKitNum = "";
                CovidActivity.covidNum = "";
                CovidActivity.notVaccinatedReason = "";
                Wellness3Activity.otherIllness = "";
                Wellness4Activity.hr = "";
                Wellness4Activity.bdUpper = "";
                Wellness4Activity.bpLower = "";
                Wellness5Activity.sugar = Double.parseDouble("");
                Wellness5Activity.cholesterol = Double.parseDouble("");
                Wellness5Activity.waist = Double.parseDouble("");
                Wellness5Activity.weight= Double.parseDouble("");
                Wellness5Activity.height = Double.parseDouble("");
                Referrals2Activity.otherReferral = "";
                Referrals2Activity.comment = "";
                FacilityReferralActivity.preferredFacility = "";
                PostTestCounsellingActivity.condomsMale = "0";
                PostTestCounsellingActivity.condomsFemale = "0";
                PostTestCounsellingActivity.lube = "";
                Intent intent = new Intent(SubmissionOverviewActivity.this,MainMenuActivity.class);
                startActivity(intent);
            }
        });

        endNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        clientName = findViewById(R.id.client_name);
        flag_yes = findViewById(R.id.flag_user_yes);
        flag_no = findViewById(R.id.flag_user_no);
        medication = findViewById(R.id.medication_field);
        flag_reason_text = findViewById(R.id.flag_reason);
        flag_container = findViewById(R.id.flag_container);

        medication.setText(medication_given);
        flag_reason_text.setText(flag_reason) ;

        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);


        if (flagged_details.equals("Yes")){
            flag_container.setVisibility(View.INVISIBLE);
        }else{
            flag_container.setVisibility(View.VISIBLE);
        }

        createGroupList();
        createCollection();

        expandableListView = findViewById(R.id.elvMobiles);
        expandableListAdapter = new MyExpandableListAdapter(this, groupList, mobileCollection);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition = -1;
            @Override
            public void onGroupExpand(int i) {
                if(lastExpandedPosition != -1 && i != lastExpandedPosition){
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = i;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String selected = expandableListAdapter.getChild(i,i1).toString();
                Toast.makeText(getApplicationContext(), "Selected: " + selected, Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TestKitResultsActivity.result.equals("Non-Reactive")){
                    hivStatus = "No";
                }
                else if(TestKitResultsActivity.result.equals("Reactive") && ConfirmationResultActivity.confrimResult.equals("Reactive")){
                    hivStatus = "Yes";
                } else if (TestKitResultsActivity.result.equals("Reactive") && ConfirmationResultActivity.confrimResult.equals("Non-Reactive")) {
                    hivStatus = "No";
                }

                if (flag_yes.isChecked()){
                    flag = flag_yes.getText().toString();
                } else if (flag_no.isChecked()) {
                    flag = flag_no.getText().toString();
                }
                medication_given = medication.getText().toString();
                flag_reason = flag_reason_text.getText().toString();




                if (flag_yes.isChecked() && flag_reason.isEmpty()){
                    Toast.makeText(SubmissionOverviewActivity.this, "Please provide a flag reason", Toast.LENGTH_SHORT).show();
                } else {
                    setData();
                    setAppointment();
                    setUserInfo();
                    setReferrals();
                    ClientDetailsActivity.name = "";
                    ClientDetailsActivity.surname = "";
                    ClientDetailsActivity.companyName = "";
                    TestTypeActivity.idType = "";
                    TestTypeActivity.idNo = "";
                    ClientDetailsActivity.cellNum = "";
                    ClientDetailsActivity.optionalNum = "";
                    DemographicsActivity.membershipNum = "";
                    TestingReasonActivity.testReason = "";
                    TestingReasonActivity.otherReasonTest = "";
                    TestKitDetailsActivity.testKitNum = "";
                    ConfirmationTestActivity.confirmTestKitNum = "";
                    CovidActivity.covidNum = "";
                    CovidActivity.notVaccinatedReason = "";
                    Wellness3Activity.otherIllness = "";
                    Wellness4Activity.hr = "";
                    Wellness4Activity.bdUpper = "";
                    Wellness4Activity.bpLower = "";
                    Wellness5Activity.sugar = Double.parseDouble(String.valueOf(0));
                    Wellness5Activity.cholesterol = Double.parseDouble(String.valueOf(0));
                    Wellness5Activity.waist = Double.parseDouble(String.valueOf(0));
                    Wellness5Activity.weight= Double.parseDouble(String.valueOf(0));
                    Wellness5Activity.height = Double.parseDouble(String.valueOf(0));
//                TB2Activity.comments = "";
                    Referrals2Activity.otherReferral = "";
                    Referrals2Activity.comment = "";
                    FacilityReferralActivity.preferredFacility = "";
                    PostTestCounsellingActivity.condomsMale = "0";
                    PostTestCounsellingActivity.condomsFemale = "0";
                    PostTestCounsellingActivity.lube = "";

                    showSuccessDialog();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);

                            SharedPreferences flag_details = getApplication().getSharedPreferences("flagged_info", Context.MODE_PRIVATE);
                            SharedPreferences flagged_details_reasons = getApplication().getSharedPreferences("flagged_info_reason", Context.MODE_PRIVATE);
                            SharedPreferences UserID = getApplication().getSharedPreferences("client_id", Context.MODE_PRIVATE);
                            SharedPreferences client_exist = getApplication().getSharedPreferences("client_exist", Context.MODE_PRIVATE);
                            SharedPreferences  dailyAppCounts = getApplication().getSharedPreferences("dailyAppCount", Context.MODE_PRIVATE);

                            SharedPreferences.Editor editor = flag_details.edit();
                            SharedPreferences.Editor editor2 = flagged_details_reasons.edit();
                            SharedPreferences.Editor editor3 = UserID.edit();
                            SharedPreferences.Editor editor4 = client_exist.edit();
                            SharedPreferences.Editor editor5 = dailyAppCounts.edit();

                            editor.remove("flagged_info");
                            editor2.remove("flagged_info_reason");
                            editor3.remove("client_id");
                            editor4.remove("client_exist");
                            editor5.remove("dailyAppCount");

                            editor.apply();
                            editor2.apply();
                            editor3.apply();
                            editor4.apply();
                            editor5.apply();

                            startActivity(intent);
                        }
                    }, 3000);
                }
            }
        });

    }

    private void createCollection() {
        String[] clientDetailModel = {"Name: "+ClientDetailsActivity.name+" "+ClientDetailsActivity.surname,"ID Type: "+TestTypeActivity.idType ,"ID/Passport Number: "+TestTypeActivity.idNo,
                "Date of Birth: "+ClientDetailsActivity.dob, "Gender: "+ClientDetailsActivity.gender,"Company Name: "+ClientDetailsActivity.companyName,
                "cellNumber:", "Industry Member: "+DemographicsActivity.industryMembership,
                "Member No: "+DemographicsActivity.membershipNum,"Key Pop Type: "+DemographicsActivity.keyPop, "Relationship: "+DemographicsActivity.relationship
                ,"Nationality: "+TestTypeActivity.nationality, "Ethnicity: "+DemographicsActivity.ethnicity};
        String[] consentFormModel = {"First HIV Test: "+TestingReasonActivity.hivTest, "Last Tested: "+TestingReasonActivity.lastTested, "Last Result: "+TestingReasonActivity.testResult
                , "Accessed Treatment: "+TestingReasonActivity.accessedTreatment,"Reason: "+TestingReasonActivity.testReason, "Rapid Test: "+InformedConsentActivity.rapidTest,
                "Home Visit: "+InformedConsentActivity.followUpVisit, "Call Center: "+InformedConsent2Activity.followUp};
        String[] hivModelsM = {"VMMC: "+CounsellingActivity.vmmc, "Refer VMMC: "+CounsellingActivity.referVmmc, "HIV Testing: "+CounsellingActivity.hivTest,
        "Test Name: "+TestKitDetailsActivity.testkitName,"Test No.: "+TestKitDetailsActivity.testKitNum,"Test Result: "+TestKitResultsActivity.result,"Confirm Test Name: "+ConfirmationTestActivity.confirmTestkitName,
                "Confirm Test No.: "+ConfirmationTestActivity.confirmTestKitNum,"Confirm Result: "+ConfirmationResultActivity.confrimResult};
        String[] hivModelsF = {"VMMC: "+CounsellingActivity.vmmc, "Refer VMMC: "+CounsellingActivity.referVmmc,"Pregnant: "+CounsellingActivity.pregnancy, "Refer Antenatal: "+CounsellingActivity.antnatal, "HIV Test"+CounsellingActivity.hivTest,
                "Test Name: "+TestKitDetailsActivity.testkitName,"Test No.: "+TestKitDetailsActivity.testKitNum,"Test Result: "+TestKitResultsActivity.result,"Confirm Test Name: "+ConfirmationTestActivity.confirmTestkitName,
                "Confirm Test No.: "+ConfirmationTestActivity.confirmTestKitNum,"Confirm Result: "+ConfirmationResultActivity.confrimResult};
        String[] hivModelsother = {"Pregnant: "+CounsellingActivity.pregnancy, "Refer Antenatal: "+CounsellingActivity.antnatal, "HIV Test"+CounsellingActivity.hivTest,
                "Test Name: "+TestKitDetailsActivity.testkitName,"Test No.: "+TestKitDetailsActivity.testKitNum,"Test Result: "+TestKitResultsActivity.result,"Confirm Test Name: "+ConfirmationTestActivity.confirmTestkitName,
                "Confirm Test No.: "+ConfirmationTestActivity.confirmTestKitNum,"Confirm Result: "+ConfirmationResultActivity.confrimResult};
        String[] screeningQuestionsModel = {"Covid: "+CovidActivity.haveCovid, "No. Covid: "+CovidActivity.covidNum, "Vaccinated: "+CovidActivity.fullyVaccinated,
                "Smoke: "+WellnessActivity.smoke,"No. Smoke: "+WellnessActivity.numCiggs,"Quit: "+WellnessActivity.quitSmoke,"Exercise"+WellnessActivity.exercise,
                "Increase Exercise"+WellnessActivity.increaseExercise,
                "Highly Stressed: "+Wellness2Activity.highlyStressed,"Manage Stress: "+Wellness2Activity.managingStress,"Sleep Problems: "+Wellness2Activity.sleepProblems,
                "Impact Illness: "+Wellness3Activity.impactIllness,"Emotional Wellness: "+Wellness3Activity.emotionalScale,"Counselling: "+Wellness3Activity.emoHealth};
        String[] wellnessModel = {"Finance Help: "+Wellness4Activity.finance, "Measurements : "+Wellness4Activity.measurement, "Lower Blood Pressure"+Wellness4Activity.bpLower,"Upper Blood Pressure: "+Wellness4Activity.bdUpper,
                "Heart Rate:"+Wellness4Activity.hr, "Blood Sugar: "+Wellness5Activity.sugar,"Cholesterol: "+Wellness5Activity.cholesterol, "Waist Circum: "+Wellness5Activity.waist,
                "Weight: "+Wellness5Activity.weight,"Height: "+Wellness5Activity.height, "TB: "+TBActivity.tb, "Mucus: "+TBActivity.mucus,
                "Long cough: "+TBActivity.cough, "Lost weight: "+TBActivity.lostWeight, "Fever/Chills/night Sweats"+TBActivity.fever,"STI: "+STIActivity.sti,
                "Burning Urine: "+STIActivity.urine,"Discharge: "+STIActivity.discharge,"Itching/blisters/sores etc.: "+STIActivity.itching,
                "Unprotected Sex: "+STIActivity.unprotected};
        String[] referralsModel = {"ELISA: "+ReferralsActivity.eilsa,"Pre-ART: "+ReferralsActivity.preArt, "ART: "+ReferralsActivity.art, "PMTCT: "+ReferralsActivity.pmtct,
                "Gender Violence: "+ReferralsActivity.genderViolence,"STI Test: "+ReferralsActivity.stiTest,
                "TB Test: "+ReferralsActivity.tbTest, "VMMC: "+ReferralsActivity.circumcision,"Family Planning: "+Referrals2Activity.family, "Antenatal: "+Referrals2Activity.antenatal,
                "Social Services: "+Referrals2Activity.social,"NCD: "+Referrals2Activity.ncd, "Other Referrals: "+Referrals2Activity.otherReferral,"Facility: "+FacilityReferralActivity.facility,
                "Preferred Facility: "+FacilityReferralActivity.preferredFacility};
        String[] postTestModel = { "Male Condoms"+PostTestCounsellingActivity.condomsMale, "Female Condoms"+PostTestCounsellingActivity.condomsFemale,
                "Lubricants"+PostTestCounsellingActivity.lube};
        mobileCollection = new HashMap<String, List<String>>();
        for(String group : groupList){
            if (group.equals("Client Details")){
                loadChild(clientDetailModel);
            } else if (group.equals("Consent Forms"))
                loadChild(consentFormModel);
            else if (ClientDetailsActivity.gender.equals("Male") && group.equals("HIV"))
                loadChild(hivModelsM);
            else if (ClientDetailsActivity.gender.equals("Female") && group.equals("HIV"))
                loadChild(hivModelsF);
            else if (ClientDetailsActivity.gender.equals("Other") && group.equals("HIV"))
                loadChild(hivModelsother);
            else if (group.equals("Screening Questions"))
                loadChild(screeningQuestionsModel);
            else if (group.equals("Wellness Questions"))
                loadChild(wellnessModel);
            else if (group.equals("Referrals"))
                loadChild(referralsModel);
            else if (group.equals("Post Test"))
                loadChild(postTestModel);
            mobileCollection.put(group, childList);
        }
    }

    private void loadChild(String[] mobileModels) {
        childList = new ArrayList<>();
        for(String model : mobileModels){
            childList.add(model);
        }
    }

    private void createGroupList() {
        groupList = new ArrayList<>();
        groupList.add("Client Details");
        groupList.add("Consent Forms");
        groupList.add("HIV");
        groupList.add("Screening Questions");
        groupList.add("Wellness Questions");
        groupList.add("Referrals");
        groupList.add("Post Test");
    }

    private void setData(){
        SharedPreferences flag_details = getApplication().getSharedPreferences("flagged_info", Context.MODE_PRIVATE);
        String flagged_details = flag_details.getString("flagged_info", "");

        SharedPreferences flag_details_reason = getApplication().getSharedPreferences("flagged_info_reason", Context.MODE_PRIVATE);
        String flagged_details_reasons = flag_details_reason.getString("flagged_info_reason", "");

        SharedPreferences nurseName_user = getApplicationContext().getSharedPreferences("nurse_name", Context.MODE_PRIVATE);
        String nurseUserName = nurseName_user.getString("nurseName", "");

        SharedPreferences nurseID_user = getApplicationContext().getSharedPreferences("nurse_id", Context.MODE_PRIVATE);
        String nurseIDName = nurseID_user.getString("nurseID", "");

        SharedPreferences LastSelected = getApplication().getSharedPreferences("Last_selected", Context.MODE_PRIVATE);
        String location = LastSelected.getString("location", "");

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        HashMap<String, Object> map = new HashMap<>();
        map.put("Appointment_ID","" +   randomID);
        map.put("Appointment_Location","" + location);
        map.put("Medication_Administered", "" + medication_given);
        if (flagged_details.equals("Yes")){
            map.put("Flagged", "" + "Yes");
            map.put("flagReason" , "" + flagged_details_reasons);
        } else
        { map.put("Flagged", "" + flag);
            map.put("flagReason" , "" + flag_reason);}
        map.put("testType", ""+TestTypeActivity.testType);
        map.put("date",""+dateTime);
        map.put("clientID",""+TestTypeActivity.idNo);
        map.put("Name", ""+ClientDetailsActivity.name);
        map.put("Surname", ""+ClientDetailsActivity.surname);
        map.put("gender",""+ClientDetailsActivity.gender);
        map.put("idType", ""+TestTypeActivity.testType);
        map.put("dateOfBirth",""+ClientDetailsActivity.dob);
        map.put("cellNumber",""+ClientDetailsActivity.cellNum);
        map.put("additionalCellNumber",""+ClientDetailsActivity.optionalNum);
        map.put("companyName",""+ClientDetailsActivity.companyName);
        map.put("industry_Member",""+DemographicsActivity.industryMembership);
        map.put("industryNumber",""+DemographicsActivity.membershipNum);
        map.put("keyPopulationType",""+DemographicsActivity.keyPop);
        map.put("nationality",""+TestTypeActivity.nationality);
        map.put("ethnicity",""+DemographicsActivity.ethnicity);
        map.put("relationshipStatus",""+DemographicsActivity.relationship);
        map.put("firstHivTest",""+TestingReasonActivity.hivTest);
        map.put("lastTested",""+TestingReasonActivity.lastTested);
        map.put("lastTestResult",""+TestingReasonActivity.testResult);
        map.put("accessedTreatment",""+TestingReasonActivity.accessedTreatment);
        map.put("lastTestedLocation",""+TestingReasonActivity.testReason);
        map.put("otherReasonsTesting",""+TestingReasonActivity.otherReasonTest);
        map.put("rapidTest",""+InformedConsentActivity.rapidTest);
        map.put("followUpHomeVisit",""+InformedConsentActivity.followUpVisit);
        map.put("hivConsent",""+InformedConsentActivity.hivConsent);
        map.put("allowAnonymousData",""+InformedConsent2Activity.anonData);
        map.put("callCenterFollowUp",""+InformedConsent2Activity.followUp);
        map.put("storageDataConsent",""+InformedConsent2Activity.anonDataConsent);

        if (ClientDetailsActivity.gender.equals("Female")){
            map.put("pregnant",""+CounsellingActivity.pregnancy);
            map.put("hivCounselling",""+CounsellingActivity.hivTest);
            map.put("antenatalCareReferral",""+CounsellingActivity.antnatal);
        }else if(ClientDetailsActivity.gender.equals("Male")) {
            map.put("voluntaryMaleMedicalCircumcision",""+CounsellingActivity.vmmc);
            map.put("hivCounselling",""+CounsellingActivity.hivTest);
            map.put("voluntaryMaleMedicalCircumcisionReferral",""+CounsellingActivity.referVmmc);
        }else {
            map.put("voluntaryMaleMedicalCircumcision",""+CounsellingActivity.vmmc);
            map.put("voluntaryMaleMedicalCircumcisionReferral",""+CounsellingActivity.referVmmc);
            map.put("pregnant",""+CounsellingActivity.pregnancy);
            map.put("hivCounselling",""+CounsellingActivity.hivTest);
            map.put("antenatalCareReferral",""+CounsellingActivity.antnatal);
        }
        map.put("firstTestKitName",""+TestKitDetailsActivity.testkitName);
        map.put("firstTestKitNumber",""+TestKitDetailsActivity.testKitNum);
        map.put("firstTestKitExpirationDate",""+TestKitDetailsActivity.testKitExpire);
        map.put("firstTestResult",""+TestKitResultsActivity.result);
        map.put("confirmationTestKitName",""+ConfirmationTestActivity.confirmTestkitName);
        map.put("confirmationTestKitNumber",""+ConfirmationTestActivity.confirmTestKitNum);
        map.put("confirmationTestKitExpirationDate",""+ConfirmationTestActivity.confirmTestKitExpire);
        map.put("confirmationTestResult",""+ConfirmationResultActivity.confrimResult);
        map.put("HIV",""+hivStatus);
        map.put("covid",""+CovidActivity.haveCovid);
        map.put("numTimesHadCovid",""+CovidActivity.covidNum);
        map.put("fullyVaccinated",""+CovidActivity.fullyVaccinated);
        map.put("reasonNotVaccinated",""+CovidActivity.notVaccinatedReason);
        map.put("smoke",""+WellnessActivity.smoke);
        map.put("numCigarettesDay",""+WellnessActivity.numCiggs);
        map.put("quitSmoking",""+WellnessActivity.quitSmoke);
        map.put("Exercise",""+WellnessActivity.exercise);
        map.put("increaseExerciseLevel",""+WellnessActivity.increaseExercise);
        map.put("highlyStressed",""+Wellness2Activity.highlyStressed);
        map.put("managingStress",""+Wellness2Activity.managingStress);
        map.put("sleepingProblems",""+Wellness2Activity.sleepProblems);
        map.put("mostImpactIllness",""+Wellness3Activity.impactIllness);
        map.put("otherIllness",""+Wellness3Activity.otherIllness);
        map.put("emotionalWellness",""+Wellness3Activity.emotionalScale);
        map.put("emotionalWellnessCounselling",""+Wellness3Activity.emoHealth);
        map.put("manageFinanceBetter",""+Wellness4Activity.finance);
        map.put("undergoWellnessMeasurement",""+Wellness4Activity.measurement);
        map.put("bloodPressureSystolicUpper",""+Wellness4Activity.bdUpper);
        map.put("bloodPressureDiastolicLower",""+Wellness4Activity.bpLower);
        map.put("bmiStatus",""+Wellness5Activity.bmiStatus);
        map.put("bmi",""+Wellness5Activity.Bmi);
        map.put("heartRate",""+Wellness4Activity.hr);
        map.put("bloodSugar",""+Wellness5Activity.sugar);
        map.put("cholesterol",""+Wellness5Activity.cholesterol);
        map.put("waistCircumference",""+Wellness5Activity.waist);
        map.put("weight",""+Wellness5Activity.weight);
        map.put("height",""+Wellness5Activity.height);
        map.put("currentTbTreatment",""+TBActivity.tb);
        map.put("nightSweats",""+TBActivity.mucus);
        map.put("coughLongerThanTwoWeeks",""+TBActivity.cough);
        map.put("weightLoss",""+TBActivity.lostWeight);
        map.put("feverPersisting",""+TBActivity.fever);
        map.put("stiTreatment",""+STIActivity.sti);
        map.put("burningPainPassingUrine",""+STIActivity.urine);
        map.put("dischargeSmells",""+STIActivity.discharge);
        map.put("itchingGenitalsAnusBlistersSoresSpotsLumps",""+STIActivity.itching);
        map.put("unprotectedSex",""+STIActivity.unprotected);
        map.put("elisa",""+ReferralsActivity.eilsa);
        map.put("preArt",""+ReferralsActivity.preArt);
        map.put("art",""+ReferralsActivity.art);
        map.put("pmtct",""+ReferralsActivity.pmtct);
        map.put("genderBasedViolence",""+ReferralsActivity.genderViolence);
        map.put("stiTest",""+ReferralsActivity.stiTest);
        map.put("tBTest",""+ReferralsActivity.tbTest);
        map.put("voluntaryMaleMedicalCircumcisionReferral",""+ReferralsActivity.circumcision);
        map.put("familyPlanning",""+Referrals2Activity.family);
        map.put("socialService",""+Referrals2Activity.social);
        map.put("antenatalCare",""+Referrals2Activity.antenatal);
        map.put("ncdsTreatmentSupport",""+Referrals2Activity.ncd);
        map.put("otherReferrals",""+Referrals2Activity.otherReferral);
        map.put("comments",""+Referrals2Activity.comment);
        map.put("referralFacility",""+FacilityReferralActivity.facility);
        map.put("preferredFacility",""+FacilityReferralActivity.preferredFacility);
        map.put("maleCondoms",""+PostTestCounsellingActivity.condomsMale);
        map.put("femaleCondoms",""+PostTestCounsellingActivity.condomsFemale);
        map.put("lubricants",""+PostTestCounsellingActivity.lube);
        map.put("postTestCounselling",""+PostTestCounsellingActivity.postTestCompleted);
        map.put("informedOfHivTestResult",""+PostTestCounsellingActivity.informedHiv);
        map.put("Nurse_Id",""+nurseIDName);
        map.put("Nurse_Name", ""+ nurseUserName);
        if (Wellness4Activity.bloodPressureStatus == "Low Blood Pressure" || Wellness4Activity.bloodPressureStatus == "Dangerously high"){
            map.put("lowerBloodPressureRetest",""+Wellness_retest2.bp_up_retest);
            map.put("upperBloodPressureRetest",""+Wellness_retest2.bp_low_retest);
        }else if (Wellness5Activity.sugarStatus == "Dangerously Low" || Wellness5Activity.sugarStatus == "Dangerously high"){
            map.put("sugarRetest",""+Wellness_retest3.sugar_retest);
            map.put("sugarStatusRetest",""+Wellness_retest3.sugarStatus_retest);
        }else if((Wellness4Activity.bloodPressureStatus == "Low Blood Pressure" || Wellness4Activity.bloodPressureStatus == "Dangerously high") && (Wellness5Activity.sugarStatus == "Dangerously Low" || Wellness5Activity.sugarStatus == "Dangerously high")) {
            map.put("sugarRetest",""+Wellness_restest.sugar_retest);
            map.put("lowerBloodPressureRetest",""+Wellness_restest.bp_low_retest);
            map.put("upperBloodPressureRetest",""+Wellness_restest.bp_up_retest);
        }
        db.collection("Clients")
                .document(""+TestTypeActivity.idNo+"/"+splitDate[2]+"/"+randomID+"/Capture Data"+"/Data")
                .set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                    }
                });
    }

    private void setAppointment (){

        SharedPreferences nurseName_user = getApplicationContext().getSharedPreferences("nurse_name", Context.MODE_PRIVATE);
        String nurseUserName = nurseName_user.getString("nurseName", "");

        SharedPreferences nurseID_user = getApplicationContext().getSharedPreferences("nurse_id", Context.MODE_PRIVATE);
        String nurseIDName = nurseID_user.getString("nurseID", "");

        SharedPreferences LastSelected = getApplication().getSharedPreferences("Last_selected", Context.MODE_PRIVATE);
        String location = LastSelected.getString("location", "");

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        HashMap<String, Object> map = new HashMap<>();
        map.put("Appointment_ID","" +   randomID);
        map.put("Appointment_Date","" + dateTime);
        map.put("Appointment_Location","" + location);
        map.put("Nurse_Id",""+nurseIDName);
        map.put("Nurse_Name", ""+ nurseUserName);
        map.put("HIV",""+hivStatus);
        map.put("Medication_Administered", "" + medication_given);
        map.put("Month", ""+ month_name);
        map.put("Day", ""+ cal.get(Calendar.DATE));
        map.put("clientID",""+TestTypeActivity.idNo);
        db.collection("Clients")
                .document(""+TestTypeActivity.idNo+"/"+splitDate[2]+"/"+randomID)
                .set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });
    }
    private void setReferrals (){


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        HashMap<String, Object> map = new HashMap<>();
        map.put("elisa",""+ReferralsActivity.eilsa);
        map.put("preArt",""+ReferralsActivity.preArt);
        map.put("art",""+ReferralsActivity.art);
        map.put("pmtct",""+ReferralsActivity.pmtct);
        map.put("genderBasedViolence",""+ReferralsActivity.genderViolence);
        map.put("stiTest",""+ReferralsActivity.stiTest);
        map.put("tBTest",""+ReferralsActivity.tbTest);
        map.put("voluntaryMaleMedicalCircumcisionReferral",""+ReferralsActivity.circumcision);
        map.put("familyPlanning",""+Referrals2Activity.family);
        map.put("socialService",""+Referrals2Activity.social);
        map.put("antenatalCare",""+Referrals2Activity.antenatal);
        map.put("ncdsTreatmentSupport",""+Referrals2Activity.ncd);
        map.put("otherReferrals",""+Referrals2Activity.otherReferral);
        map.put("comments",""+Referrals2Activity.comment);
        map.put("referralFacility",""+FacilityReferralActivity.facility);
        map.put("preferredFacility",""+FacilityReferralActivity.preferredFacility);


        db.collection("Clients")
                .document(""+TestTypeActivity.idNo+"/"+"Referrals"+"/"+"Referral Details")
                .set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });
    }

    private void setUserInfo (){

        SharedPreferences LastSelected = getApplication().getSharedPreferences("Last_selected", Context.MODE_PRIVATE);
        String location = LastSelected.getString("location", "");

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();

        SharedPreferences flag_details = getApplication().getSharedPreferences("flagged_info", Context.MODE_PRIVATE);
        String flagged_details = flag_details.getString("flagged_info", "");

        SharedPreferences flag_details_reason = getApplication().getSharedPreferences("flagged_info_reason", Context.MODE_PRIVATE);
        String flagged_details_reasons = flag_details_reason.getString("flagged_info_reason", "");

        SharedPreferences dailyAppCount = getApplication().getSharedPreferences("dailyAppCount", Context.MODE_PRIVATE);
        String dailyAppCounts = dailyAppCount.getString("dailyAppCount", "");

        SharedPreferences nurseID_user = getApplicationContext().getSharedPreferences("nurse_id", Context.MODE_PRIVATE);
        String nurseIDName = nurseID_user.getString("nurseID", "");

        db.setFirestoreSettings(settings);

        HashMap<String, Object> map = new HashMap<>();

        if (flagged_details.equals("Yes")){
            map.put("Flagged", "" + "Yes");
            map.put("flagReason" , "" + flagged_details_reasons);
        } else
        { map.put("Flagged", "" + flag);
            map.put("flagReason" , "" + flag_reason);}
        map.put("DailyAppCount","" +  dailyAppCounts);
        map.put("lastAppointment", "" + dateTime);
        map.put("HIV",""+hivStatus);
        map.put("clientID",""+TestTypeActivity.idNo);
        map.put("Name", ""+ClientDetailsActivity.name);
        map.put("Surname" , "" + ClientDetailsActivity.surname);
        map.put("gender",""+ClientDetailsActivity.gender);
        map.put("idType", ""+TestTypeActivity.testType);
        map.put("dateOfBirth",""+ClientDetailsActivity.dob);
        map.put("cellNumber",""+ClientDetailsActivity.cellNum);
        map.put("additionalCellNumber",""+ClientDetailsActivity.optionalNum);
        map.put("companyName",""+ClientDetailsActivity.companyName);
        map.put("industry_Member",""+DemographicsActivity.industryMembership);
        map.put("industryNumber",""+DemographicsActivity.membershipNum);
        map.put("keyPopulationType",""+DemographicsActivity.keyPop);
        map.put("nationality",""+TestTypeActivity.nationality);
        map.put("ethnicity",""+DemographicsActivity.ethnicity);
        map.put("lastAppointmentlocation",""+location);
        map.put("LastSeenBy",""+nurseIDName);
        db.collection("Clients")
                .document(""+TestTypeActivity.idNo)
                .set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });


    }
    private void showSuccessDialog(){

        ConstraintLayout errorConstraintLayout = findViewById(R.id.submission_success);
        View view = LayoutInflater.from(SubmissionOverviewActivity.this).inflate(R.layout.submission_success, errorConstraintLayout);


        AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionOverviewActivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        if(alertDialog.getWindow()!= null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }


    @Override
    public void onBackPressed() {

        dialog.show();

    }


}