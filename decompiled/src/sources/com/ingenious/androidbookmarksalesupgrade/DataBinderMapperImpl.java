package com.ingenious.androidbookmarksalesupgrade;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityAddCustomerBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityAddVisitBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityAllProductsBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityChatBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityCheckInBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityCompleteVisitBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityForgetPasswordBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityHomeBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityLocationBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityLoginBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityLowStockBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityOtpverificationBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityProfileBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityRefillRequestsBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityResetPasswordBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityVisitAdoptionBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityVisitDetailsBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.BottomSheetAdoptionDetailsFragmentBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.BottomSheetAllProductsCartFragmentBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.BottomSheetCustomerDetailsBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.BottomSheetRefillRequestProductDetailsBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogActionTypeFilterBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogAddHomeBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogAddedByFilterBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogAreaFilterBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogByAreaFilterBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogCustomerInventoryMoreBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogCustomerTypeFilterBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogDateRangeFilterBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogDistanceFilterBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogFilterMainBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogFilterMainInventoryBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogFilterMainInventoryGradeBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogFilterMainInventorySegmentBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogFilterMainInventorySubjectBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogInvoiceShareBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogJobStartBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogLastVisitFilterBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogPerformanceFilterMainBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogPriorityFilterBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogRecentActivtyFilterMainBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogRefillRequestBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogRequestToEditBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogSignoutBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentApprovedRefillRequestsBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentCheckIn1BindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentCheckIn2BindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentCheckIn3BindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentCompleteVisitBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentCustomerBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentHomeBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentInventoryBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentLowStockSelectionBottomSheetBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentPendingRefillRequestsBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentReceivedRefillRequestsBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitAdoption1BindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitAdoption2BindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitAdoptionBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitAdoptionBooksBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitAdoptionGradesBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitAdoptionQuantityBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitAdoptionSubjectsBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitDetailsBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitHistoryBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitSamplesBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemAdoptionBooksBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemAdoptionImagesBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemAdoptionListBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemAdoptionQuantitiesBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemAllProductsBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemAllProductsCart2BindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemAllProductsCartBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemApprovedVisitsListBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemAttachmentBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemBooksListBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemCompleteVisitBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemCustomerListFilterBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemCustomersListBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemCustomersSelectionListBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemGradesSubjectsBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemInventoryLowStockBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemInventoryProductsBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemLowStockProductsBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemMessageReceivedBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemMessageSentBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemPastVisitsListBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemRequestListBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemSampleListBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemSegmentsBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemSegmentsSelectionsBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemStockListBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemVisitsListBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.LayoutHeaderBindingImpl;
import com.ingenious.androidbookmarksalesupgrade.databinding.LayoutHeaderGenericBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes12.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final int LAYOUT_ACTIVITYADDCUSTOMER = 1;
    private static final int LAYOUT_ACTIVITYADDVISIT = 2;
    private static final int LAYOUT_ACTIVITYALLPRODUCTS = 3;
    private static final int LAYOUT_ACTIVITYCHAT = 4;
    private static final int LAYOUT_ACTIVITYCHECKIN = 5;
    private static final int LAYOUT_ACTIVITYCOMPLETEVISIT = 6;
    private static final int LAYOUT_ACTIVITYFORGETPASSWORD = 7;
    private static final int LAYOUT_ACTIVITYHOME = 8;
    private static final int LAYOUT_ACTIVITYLOCATION = 9;
    private static final int LAYOUT_ACTIVITYLOGIN = 10;
    private static final int LAYOUT_ACTIVITYLOWSTOCK = 11;
    private static final int LAYOUT_ACTIVITYOTPVERIFICATION = 12;
    private static final int LAYOUT_ACTIVITYPROFILE = 13;
    private static final int LAYOUT_ACTIVITYREFILLREQUESTS = 14;
    private static final int LAYOUT_ACTIVITYRESETPASSWORD = 15;
    private static final int LAYOUT_ACTIVITYVISITADOPTION = 16;
    private static final int LAYOUT_ACTIVITYVISITDETAILS = 17;
    private static final int LAYOUT_BOTTOMSHEETADOPTIONDETAILSFRAGMENT = 18;
    private static final int LAYOUT_BOTTOMSHEETALLPRODUCTSCARTFRAGMENT = 19;
    private static final int LAYOUT_BOTTOMSHEETCUSTOMERDETAILS = 20;
    private static final int LAYOUT_BOTTOMSHEETREFILLREQUESTPRODUCTDETAILS = 21;
    private static final int LAYOUT_DIALOGACTIONTYPEFILTER = 22;
    private static final int LAYOUT_DIALOGADDEDBYFILTER = 24;
    private static final int LAYOUT_DIALOGADDHOME = 23;
    private static final int LAYOUT_DIALOGAREAFILTER = 25;
    private static final int LAYOUT_DIALOGBYAREAFILTER = 26;
    private static final int LAYOUT_DIALOGCUSTOMERINVENTORYMORE = 27;
    private static final int LAYOUT_DIALOGCUSTOMERTYPEFILTER = 28;
    private static final int LAYOUT_DIALOGDATERANGEFILTER = 29;
    private static final int LAYOUT_DIALOGDISTANCEFILTER = 30;
    private static final int LAYOUT_DIALOGFILTERMAIN = 31;
    private static final int LAYOUT_DIALOGFILTERMAININVENTORY = 32;
    private static final int LAYOUT_DIALOGFILTERMAININVENTORYGRADE = 33;
    private static final int LAYOUT_DIALOGFILTERMAININVENTORYSEGMENT = 34;
    private static final int LAYOUT_DIALOGFILTERMAININVENTORYSUBJECT = 35;
    private static final int LAYOUT_DIALOGINVOICESHARE = 36;
    private static final int LAYOUT_DIALOGJOBSTART = 37;
    private static final int LAYOUT_DIALOGLASTVISITFILTER = 38;
    private static final int LAYOUT_DIALOGPERFORMANCEFILTERMAIN = 39;
    private static final int LAYOUT_DIALOGPRIORITYFILTER = 40;
    private static final int LAYOUT_DIALOGRECENTACTIVTYFILTERMAIN = 41;
    private static final int LAYOUT_DIALOGREFILLREQUEST = 42;
    private static final int LAYOUT_DIALOGREQUESTTOEDIT = 43;
    private static final int LAYOUT_DIALOGSIGNOUT = 44;
    private static final int LAYOUT_FRAGMENTAPPROVEDREFILLREQUESTS = 45;
    private static final int LAYOUT_FRAGMENTCHECKIN1 = 46;
    private static final int LAYOUT_FRAGMENTCHECKIN2 = 47;
    private static final int LAYOUT_FRAGMENTCHECKIN3 = 48;
    private static final int LAYOUT_FRAGMENTCOMPLETEVISIT = 49;
    private static final int LAYOUT_FRAGMENTCUSTOMER = 50;
    private static final int LAYOUT_FRAGMENTHOME = 51;
    private static final int LAYOUT_FRAGMENTINVENTORY = 52;
    private static final int LAYOUT_FRAGMENTLOWSTOCKSELECTIONBOTTOMSHEET = 53;
    private static final int LAYOUT_FRAGMENTPENDINGREFILLREQUESTS = 54;
    private static final int LAYOUT_FRAGMENTRECEIVEDREFILLREQUESTS = 55;
    private static final int LAYOUT_FRAGMENTVISITADOPTION = 56;
    private static final int LAYOUT_FRAGMENTVISITADOPTION1 = 57;
    private static final int LAYOUT_FRAGMENTVISITADOPTION2 = 58;
    private static final int LAYOUT_FRAGMENTVISITADOPTIONBOOKS = 59;
    private static final int LAYOUT_FRAGMENTVISITADOPTIONGRADES = 60;
    private static final int LAYOUT_FRAGMENTVISITADOPTIONQUANTITY = 61;
    private static final int LAYOUT_FRAGMENTVISITADOPTIONSUBJECTS = 62;
    private static final int LAYOUT_FRAGMENTVISITDETAILS = 63;
    private static final int LAYOUT_FRAGMENTVISITHISTORY = 64;
    private static final int LAYOUT_FRAGMENTVISITSAMPLES = 65;
    private static final int LAYOUT_ITEMADOPTIONBOOKS = 66;
    private static final int LAYOUT_ITEMADOPTIONIMAGES = 67;
    private static final int LAYOUT_ITEMADOPTIONLIST = 68;
    private static final int LAYOUT_ITEMADOPTIONQUANTITIES = 69;
    private static final int LAYOUT_ITEMALLPRODUCTS = 70;
    private static final int LAYOUT_ITEMALLPRODUCTSCART = 71;
    private static final int LAYOUT_ITEMALLPRODUCTSCART2 = 72;
    private static final int LAYOUT_ITEMAPPROVEDVISITSLIST = 73;
    private static final int LAYOUT_ITEMATTACHMENT = 74;
    private static final int LAYOUT_ITEMBOOKSLIST = 75;
    private static final int LAYOUT_ITEMCOMPLETEVISIT = 76;
    private static final int LAYOUT_ITEMCUSTOMERLISTFILTER = 77;
    private static final int LAYOUT_ITEMCUSTOMERSLIST = 78;
    private static final int LAYOUT_ITEMCUSTOMERSSELECTIONLIST = 79;
    private static final int LAYOUT_ITEMGRADESSUBJECTS = 80;
    private static final int LAYOUT_ITEMINVENTORYLOWSTOCK = 81;
    private static final int LAYOUT_ITEMINVENTORYPRODUCTS = 82;
    private static final int LAYOUT_ITEMLOWSTOCKPRODUCTS = 83;
    private static final int LAYOUT_ITEMMESSAGERECEIVED = 84;
    private static final int LAYOUT_ITEMMESSAGESENT = 85;
    private static final int LAYOUT_ITEMPASTVISITSLIST = 86;
    private static final int LAYOUT_ITEMREQUESTLIST = 87;
    private static final int LAYOUT_ITEMSAMPLELIST = 88;
    private static final int LAYOUT_ITEMSEGMENTS = 89;
    private static final int LAYOUT_ITEMSEGMENTSSELECTIONS = 90;
    private static final int LAYOUT_ITEMSTOCKLIST = 91;
    private static final int LAYOUT_ITEMVISITSLIST = 92;
    private static final int LAYOUT_LAYOUTHEADER = 93;
    private static final int LAYOUT_LAYOUTHEADERGENERIC = 94;
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(LAYOUT_LAYOUTHEADERGENERIC);

    static {
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_add_customer, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_add_visit, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_all_products, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_chat, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_check_in, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_complete_visit, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_forget_password, 7);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_home, 8);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_location, 9);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_login, 10);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_low_stock, 11);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_otpverification, 12);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_profile, 13);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_refill_requests, 14);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_reset_password, 15);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_visit_adoption, 16);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_visit_details, 17);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bottom_sheet_adoption_details_fragment, 18);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bottom_sheet_all_products_cart_fragment, 19);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bottom_sheet_customer_details, 20);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bottom_sheet_refill_request_product_details, 21);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_action_type_filter, 22);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_add_home, 23);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_added_by_filter, 24);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_area_filter, 25);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_by_area_filter, 26);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_customer_inventory_more, 27);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_customer_type_filter, 28);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_date_range_filter, 29);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_distance_filter, 30);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_filter_main, 31);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_filter_main_inventory, 32);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_filter_main_inventory_grade, 33);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_filter_main_inventory_segment, 34);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_filter_main_inventory_subject, 35);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_invoice_share, 36);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_job_start, 37);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_last_visit_filter, 38);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_performance_filter_main, 39);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_priority_filter, 40);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_recent_activty_filter_main, 41);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_refill_request, 42);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_request_to_edit, 43);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_signout, 44);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_approved_refill_requests, 45);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_check_in1, 46);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_check_in2, 47);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_check_in3, 48);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_complete_visit, 49);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_customer, 50);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_home, 51);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_inventory, 52);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_low_stock_selection_bottom_sheet, 53);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_pending_refill_requests, 54);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_received_refill_requests, 55);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_visit_adoption, LAYOUT_FRAGMENTVISITADOPTION);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_visit_adoption1, LAYOUT_FRAGMENTVISITADOPTION1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_visit_adoption2, LAYOUT_FRAGMENTVISITADOPTION2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_visit_adoption_books, LAYOUT_FRAGMENTVISITADOPTIONBOOKS);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_visit_adoption_grades, 60);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_visit_adoption_quantity, 61);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_visit_adoption_subjects, LAYOUT_FRAGMENTVISITADOPTIONSUBJECTS);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_visit_details, 63);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_visit_history, 64);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_visit_samples, 65);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_adoption_books, 66);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_adoption_images, 67);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_adoption_list, LAYOUT_ITEMADOPTIONLIST);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_adoption_quantities, 69);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_all_products, LAYOUT_ITEMALLPRODUCTS);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_all_products_cart, LAYOUT_ITEMALLPRODUCTSCART);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_all_products_cart_2, LAYOUT_ITEMALLPRODUCTSCART2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_approved_visits_list, LAYOUT_ITEMAPPROVEDVISITSLIST);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_attachment, LAYOUT_ITEMATTACHMENT);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_books_list, LAYOUT_ITEMBOOKSLIST);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_complete_visit, 76);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_customer_list_filter, LAYOUT_ITEMCUSTOMERLISTFILTER);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_customers_list, LAYOUT_ITEMCUSTOMERSLIST);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_customers_selection_list, LAYOUT_ITEMCUSTOMERSSELECTIONLIST);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_grades_subjects, 80);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_inventory_low_stock, LAYOUT_ITEMINVENTORYLOWSTOCK);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_inventory_products, LAYOUT_ITEMINVENTORYPRODUCTS);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_low_stock_products, LAYOUT_ITEMLOWSTOCKPRODUCTS);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_message_received, LAYOUT_ITEMMESSAGERECEIVED);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_message_sent, LAYOUT_ITEMMESSAGESENT);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_past_visits_list, LAYOUT_ITEMPASTVISITSLIST);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_request_list, LAYOUT_ITEMREQUESTLIST);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_sample_list, LAYOUT_ITEMSAMPLELIST);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_segments, LAYOUT_ITEMSEGMENTS);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_segments_selections, 90);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_stock_list, LAYOUT_ITEMSTOCKLIST);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_visits_list, LAYOUT_ITEMVISITSLIST);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.layout_header, LAYOUT_LAYOUTHEADER);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.layout_header_generic, LAYOUT_LAYOUTHEADERGENERIC);
    }

    private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 1:
                if ("layout/activity_add_customer_0".equals(tag)) {
                    return new ActivityAddCustomerBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_add_customer is invalid. Received: " + tag);
            case 2:
                if ("layout/activity_add_visit_0".equals(tag)) {
                    return new ActivityAddVisitBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_add_visit is invalid. Received: " + tag);
            case 3:
                if ("layout/activity_all_products_0".equals(tag)) {
                    return new ActivityAllProductsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_all_products is invalid. Received: " + tag);
            case 4:
                if ("layout/activity_chat_0".equals(tag)) {
                    return new ActivityChatBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_chat is invalid. Received: " + tag);
            case 5:
                if ("layout/activity_check_in_0".equals(tag)) {
                    return new ActivityCheckInBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_check_in is invalid. Received: " + tag);
            case 6:
                if ("layout/activity_complete_visit_0".equals(tag)) {
                    return new ActivityCompleteVisitBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_complete_visit is invalid. Received: " + tag);
            case 7:
                if ("layout/activity_forget_password_0".equals(tag)) {
                    return new ActivityForgetPasswordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_forget_password is invalid. Received: " + tag);
            case 8:
                if ("layout/activity_home_0".equals(tag)) {
                    return new ActivityHomeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_home is invalid. Received: " + tag);
            case 9:
                if ("layout/activity_location_0".equals(tag)) {
                    return new ActivityLocationBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_location is invalid. Received: " + tag);
            case 10:
                if ("layout/activity_login_0".equals(tag)) {
                    return new ActivityLoginBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
            case 11:
                if ("layout/activity_low_stock_0".equals(tag)) {
                    return new ActivityLowStockBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_low_stock is invalid. Received: " + tag);
            case 12:
                if ("layout/activity_otpverification_0".equals(tag)) {
                    return new ActivityOtpverificationBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_otpverification is invalid. Received: " + tag);
            case 13:
                if ("layout/activity_profile_0".equals(tag)) {
                    return new ActivityProfileBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_profile is invalid. Received: " + tag);
            case 14:
                if ("layout/activity_refill_requests_0".equals(tag)) {
                    return new ActivityRefillRequestsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_refill_requests is invalid. Received: " + tag);
            case 15:
                if ("layout/activity_reset_password_0".equals(tag)) {
                    return new ActivityResetPasswordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_reset_password is invalid. Received: " + tag);
            case 16:
                if ("layout/activity_visit_adoption_0".equals(tag)) {
                    return new ActivityVisitAdoptionBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_visit_adoption is invalid. Received: " + tag);
            case 17:
                if ("layout/activity_visit_details_0".equals(tag)) {
                    return new ActivityVisitDetailsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_visit_details is invalid. Received: " + tag);
            case 18:
                if ("layout/bottom_sheet_adoption_details_fragment_0".equals(tag)) {
                    return new BottomSheetAdoptionDetailsFragmentBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bottom_sheet_adoption_details_fragment is invalid. Received: " + tag);
            case 19:
                if ("layout/bottom_sheet_all_products_cart_fragment_0".equals(tag)) {
                    return new BottomSheetAllProductsCartFragmentBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bottom_sheet_all_products_cart_fragment is invalid. Received: " + tag);
            case 20:
                if ("layout/bottom_sheet_customer_details_0".equals(tag)) {
                    return new BottomSheetCustomerDetailsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bottom_sheet_customer_details is invalid. Received: " + tag);
            case 21:
                if ("layout/bottom_sheet_refill_request_product_details_0".equals(tag)) {
                    return new BottomSheetRefillRequestProductDetailsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bottom_sheet_refill_request_product_details is invalid. Received: " + tag);
            case 22:
                if ("layout/dialog_action_type_filter_0".equals(tag)) {
                    return new DialogActionTypeFilterBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_action_type_filter is invalid. Received: " + tag);
            case 23:
                if ("layout/dialog_add_home_0".equals(tag)) {
                    return new DialogAddHomeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_add_home is invalid. Received: " + tag);
            case 24:
                if ("layout/dialog_added_by_filter_0".equals(tag)) {
                    return new DialogAddedByFilterBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_added_by_filter is invalid. Received: " + tag);
            case 25:
                if ("layout/dialog_area_filter_0".equals(tag)) {
                    return new DialogAreaFilterBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_area_filter is invalid. Received: " + tag);
            case 26:
                if ("layout/dialog_by_area_filter_0".equals(tag)) {
                    return new DialogByAreaFilterBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_by_area_filter is invalid. Received: " + tag);
            case 27:
                if ("layout/dialog_customer_inventory_more_0".equals(tag)) {
                    return new DialogCustomerInventoryMoreBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_customer_inventory_more is invalid. Received: " + tag);
            case 28:
                if ("layout/dialog_customer_type_filter_0".equals(tag)) {
                    return new DialogCustomerTypeFilterBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_customer_type_filter is invalid. Received: " + tag);
            case 29:
                if ("layout/dialog_date_range_filter_0".equals(tag)) {
                    return new DialogDateRangeFilterBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_date_range_filter is invalid. Received: " + tag);
            case 30:
                if ("layout/dialog_distance_filter_0".equals(tag)) {
                    return new DialogDistanceFilterBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_distance_filter is invalid. Received: " + tag);
            case 31:
                if ("layout/dialog_filter_main_0".equals(tag)) {
                    return new DialogFilterMainBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_filter_main is invalid. Received: " + tag);
            case 32:
                if ("layout/dialog_filter_main_inventory_0".equals(tag)) {
                    return new DialogFilterMainInventoryBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_filter_main_inventory is invalid. Received: " + tag);
            case 33:
                if ("layout/dialog_filter_main_inventory_grade_0".equals(tag)) {
                    return new DialogFilterMainInventoryGradeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_filter_main_inventory_grade is invalid. Received: " + tag);
            case 34:
                if ("layout/dialog_filter_main_inventory_segment_0".equals(tag)) {
                    return new DialogFilterMainInventorySegmentBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_filter_main_inventory_segment is invalid. Received: " + tag);
            case 35:
                if ("layout/dialog_filter_main_inventory_subject_0".equals(tag)) {
                    return new DialogFilterMainInventorySubjectBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_filter_main_inventory_subject is invalid. Received: " + tag);
            case 36:
                if ("layout/dialog_invoice_share_0".equals(tag)) {
                    return new DialogInvoiceShareBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_invoice_share is invalid. Received: " + tag);
            case 37:
                if ("layout/dialog_job_start_0".equals(tag)) {
                    return new DialogJobStartBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_job_start is invalid. Received: " + tag);
            case 38:
                if ("layout/dialog_last_visit_filter_0".equals(tag)) {
                    return new DialogLastVisitFilterBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_last_visit_filter is invalid. Received: " + tag);
            case 39:
                if ("layout/dialog_performance_filter_main_0".equals(tag)) {
                    return new DialogPerformanceFilterMainBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_performance_filter_main is invalid. Received: " + tag);
            case 40:
                if ("layout/dialog_priority_filter_0".equals(tag)) {
                    return new DialogPriorityFilterBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_priority_filter is invalid. Received: " + tag);
            case 41:
                if ("layout/dialog_recent_activty_filter_main_0".equals(tag)) {
                    return new DialogRecentActivtyFilterMainBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_recent_activty_filter_main is invalid. Received: " + tag);
            case 42:
                if ("layout/dialog_refill_request_0".equals(tag)) {
                    return new DialogRefillRequestBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_refill_request is invalid. Received: " + tag);
            case 43:
                if ("layout/dialog_request_to_edit_0".equals(tag)) {
                    return new DialogRequestToEditBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_request_to_edit is invalid. Received: " + tag);
            case 44:
                if ("layout/dialog_signout_0".equals(tag)) {
                    return new DialogSignoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_signout is invalid. Received: " + tag);
            case 45:
                if ("layout/fragment_approved_refill_requests_0".equals(tag)) {
                    return new FragmentApprovedRefillRequestsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_approved_refill_requests is invalid. Received: " + tag);
            case 46:
                if ("layout/fragment_check_in1_0".equals(tag)) {
                    return new FragmentCheckIn1BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_check_in1 is invalid. Received: " + tag);
            case 47:
                if ("layout/fragment_check_in2_0".equals(tag)) {
                    return new FragmentCheckIn2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_check_in2 is invalid. Received: " + tag);
            case 48:
                if ("layout/fragment_check_in3_0".equals(tag)) {
                    return new FragmentCheckIn3BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_check_in3 is invalid. Received: " + tag);
            case 49:
                if ("layout/fragment_complete_visit_0".equals(tag)) {
                    return new FragmentCompleteVisitBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_complete_visit is invalid. Received: " + tag);
            case 50:
                if ("layout/fragment_customer_0".equals(tag)) {
                    return new FragmentCustomerBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_customer is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 51:
                if ("layout/fragment_home_0".equals(tag)) {
                    return new FragmentHomeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
            case 52:
                if ("layout/fragment_inventory_0".equals(tag)) {
                    return new FragmentInventoryBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_inventory is invalid. Received: " + tag);
            case 53:
                if ("layout/fragment_low_stock_selection_bottom_sheet_0".equals(tag)) {
                    return new FragmentLowStockSelectionBottomSheetBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_low_stock_selection_bottom_sheet is invalid. Received: " + tag);
            case 54:
                if ("layout/fragment_pending_refill_requests_0".equals(tag)) {
                    return new FragmentPendingRefillRequestsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_pending_refill_requests is invalid. Received: " + tag);
            case 55:
                if ("layout/fragment_received_refill_requests_0".equals(tag)) {
                    return new FragmentReceivedRefillRequestsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_received_refill_requests is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTVISITADOPTION /* 56 */:
                if ("layout/fragment_visit_adoption_0".equals(tag)) {
                    return new FragmentVisitAdoptionBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_visit_adoption is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTVISITADOPTION1 /* 57 */:
                if ("layout/fragment_visit_adoption1_0".equals(tag)) {
                    return new FragmentVisitAdoption1BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_visit_adoption1 is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTVISITADOPTION2 /* 58 */:
                if ("layout/fragment_visit_adoption2_0".equals(tag)) {
                    return new FragmentVisitAdoption2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_visit_adoption2 is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTVISITADOPTIONBOOKS /* 59 */:
                if ("layout/fragment_visit_adoption_books_0".equals(tag)) {
                    return new FragmentVisitAdoptionBooksBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_visit_adoption_books is invalid. Received: " + tag);
            case 60:
                if ("layout/fragment_visit_adoption_grades_0".equals(tag)) {
                    return new FragmentVisitAdoptionGradesBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_visit_adoption_grades is invalid. Received: " + tag);
            case 61:
                if ("layout/fragment_visit_adoption_quantity_0".equals(tag)) {
                    return new FragmentVisitAdoptionQuantityBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_visit_adoption_quantity is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTVISITADOPTIONSUBJECTS /* 62 */:
                if ("layout/fragment_visit_adoption_subjects_0".equals(tag)) {
                    return new FragmentVisitAdoptionSubjectsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_visit_adoption_subjects is invalid. Received: " + tag);
            case 63:
                if ("layout/fragment_visit_details_0".equals(tag)) {
                    return new FragmentVisitDetailsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_visit_details is invalid. Received: " + tag);
            case 64:
                if ("layout/fragment_visit_history_0".equals(tag)) {
                    return new FragmentVisitHistoryBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_visit_history is invalid. Received: " + tag);
            case 65:
                if ("layout/fragment_visit_samples_0".equals(tag)) {
                    return new FragmentVisitSamplesBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_visit_samples is invalid. Received: " + tag);
            case 66:
                if ("layout/item_adoption_books_0".equals(tag)) {
                    return new ItemAdoptionBooksBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_adoption_books is invalid. Received: " + tag);
            case 67:
                if ("layout/item_adoption_images_0".equals(tag)) {
                    return new ItemAdoptionImagesBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_adoption_images is invalid. Received: " + tag);
            case LAYOUT_ITEMADOPTIONLIST /* 68 */:
                if ("layout/item_adoption_list_0".equals(tag)) {
                    return new ItemAdoptionListBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_adoption_list is invalid. Received: " + tag);
            case 69:
                if ("layout/item_adoption_quantities_0".equals(tag)) {
                    return new ItemAdoptionQuantitiesBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_adoption_quantities is invalid. Received: " + tag);
            case LAYOUT_ITEMALLPRODUCTS /* 70 */:
                if ("layout/item_all_products_0".equals(tag)) {
                    return new ItemAllProductsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_all_products is invalid. Received: " + tag);
            case LAYOUT_ITEMALLPRODUCTSCART /* 71 */:
                if ("layout/item_all_products_cart_0".equals(tag)) {
                    return new ItemAllProductsCartBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_all_products_cart is invalid. Received: " + tag);
            case LAYOUT_ITEMALLPRODUCTSCART2 /* 72 */:
                if ("layout/item_all_products_cart_2_0".equals(tag)) {
                    return new ItemAllProductsCart2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_all_products_cart_2 is invalid. Received: " + tag);
            case LAYOUT_ITEMAPPROVEDVISITSLIST /* 73 */:
                if ("layout/item_approved_visits_list_0".equals(tag)) {
                    return new ItemApprovedVisitsListBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_approved_visits_list is invalid. Received: " + tag);
            case LAYOUT_ITEMATTACHMENT /* 74 */:
                if ("layout/item_attachment_0".equals(tag)) {
                    return new ItemAttachmentBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_attachment is invalid. Received: " + tag);
            case LAYOUT_ITEMBOOKSLIST /* 75 */:
                if ("layout/item_books_list_0".equals(tag)) {
                    return new ItemBooksListBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_books_list is invalid. Received: " + tag);
            case 76:
                if ("layout/item_complete_visit_0".equals(tag)) {
                    return new ItemCompleteVisitBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_complete_visit is invalid. Received: " + tag);
            case LAYOUT_ITEMCUSTOMERLISTFILTER /* 77 */:
                if ("layout/item_customer_list_filter_0".equals(tag)) {
                    return new ItemCustomerListFilterBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_customer_list_filter is invalid. Received: " + tag);
            case LAYOUT_ITEMCUSTOMERSLIST /* 78 */:
                if ("layout/item_customers_list_0".equals(tag)) {
                    return new ItemCustomersListBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_customers_list is invalid. Received: " + tag);
            case LAYOUT_ITEMCUSTOMERSSELECTIONLIST /* 79 */:
                if ("layout/item_customers_selection_list_0".equals(tag)) {
                    return new ItemCustomersSelectionListBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_customers_selection_list is invalid. Received: " + tag);
            case 80:
                if ("layout/item_grades_subjects_0".equals(tag)) {
                    return new ItemGradesSubjectsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_grades_subjects is invalid. Received: " + tag);
            case LAYOUT_ITEMINVENTORYLOWSTOCK /* 81 */:
                if ("layout/item_inventory_low_stock_0".equals(tag)) {
                    return new ItemInventoryLowStockBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_inventory_low_stock is invalid. Received: " + tag);
            case LAYOUT_ITEMINVENTORYPRODUCTS /* 82 */:
                if ("layout/item_inventory_products_0".equals(tag)) {
                    return new ItemInventoryProductsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_inventory_products is invalid. Received: " + tag);
            case LAYOUT_ITEMLOWSTOCKPRODUCTS /* 83 */:
                if ("layout/item_low_stock_products_0".equals(tag)) {
                    return new ItemLowStockProductsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_low_stock_products is invalid. Received: " + tag);
            case LAYOUT_ITEMMESSAGERECEIVED /* 84 */:
                if ("layout/item_message_received_0".equals(tag)) {
                    return new ItemMessageReceivedBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_message_received is invalid. Received: " + tag);
            case LAYOUT_ITEMMESSAGESENT /* 85 */:
                if ("layout/item_message_sent_0".equals(tag)) {
                    return new ItemMessageSentBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_message_sent is invalid. Received: " + tag);
            case LAYOUT_ITEMPASTVISITSLIST /* 86 */:
                if ("layout/item_past_visits_list_0".equals(tag)) {
                    return new ItemPastVisitsListBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_past_visits_list is invalid. Received: " + tag);
            case LAYOUT_ITEMREQUESTLIST /* 87 */:
                if ("layout/item_request_list_0".equals(tag)) {
                    return new ItemRequestListBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_request_list is invalid. Received: " + tag);
            case LAYOUT_ITEMSAMPLELIST /* 88 */:
                if ("layout/item_sample_list_0".equals(tag)) {
                    return new ItemSampleListBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_sample_list is invalid. Received: " + tag);
            case LAYOUT_ITEMSEGMENTS /* 89 */:
                if ("layout/item_segments_0".equals(tag)) {
                    return new ItemSegmentsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_segments is invalid. Received: " + tag);
            case 90:
                if ("layout/item_segments_selections_0".equals(tag)) {
                    return new ItemSegmentsSelectionsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_segments_selections is invalid. Received: " + tag);
            case LAYOUT_ITEMSTOCKLIST /* 91 */:
                if ("layout/item_stock_list_0".equals(tag)) {
                    return new ItemStockListBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_stock_list is invalid. Received: " + tag);
            case LAYOUT_ITEMVISITSLIST /* 92 */:
                if ("layout/item_visits_list_0".equals(tag)) {
                    return new ItemVisitsListBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_visits_list is invalid. Received: " + tag);
            case LAYOUT_LAYOUTHEADER /* 93 */:
                if ("layout/layout_header_0".equals(tag)) {
                    return new LayoutHeaderBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for layout_header is invalid. Received: " + tag);
            case LAYOUT_LAYOUTHEADERGENERIC /* 94 */:
                if ("layout/layout_header_generic_0".equals(tag)) {
                    return new LayoutHeaderGenericBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for layout_header_generic is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
        int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
        if (localizedLayoutId > 0) {
            Object tag = view.getTag();
            if (tag == null) {
                throw new RuntimeException("view must have a tag");
            }
            int methodIndex = (localizedLayoutId - 1) / 50;
            switch (methodIndex) {
                case 0:
                    return internalGetViewDataBinding0(component, view, localizedLayoutId, tag);
                case 1:
                    return internalGetViewDataBinding1(component, view, localizedLayoutId, tag);
                default:
                    return null;
            }
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
        if (views == null || views.length == 0) {
            return null;
        }
        int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
        if (localizedLayoutId > 0) {
            Object tag = views[0].getTag();
            if (tag == null) {
                throw new RuntimeException("view must have a tag");
            }
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String tag) {
        Integer tmpVal;
        if (tag == null || (tmpVal = InnerLayoutIdLookup.sKeys.get(tag)) == null) {
            return 0;
        }
        return tmpVal.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int localId) {
        String tmpVal = InnerBrLookup.sKeys.get(localId);
        return tmpVal;
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList<DataBinderMapper> result = new ArrayList<>(2);
        result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        result.add(new com.github.gcacace.signaturepad.DataBinderMapperImpl());
        return result;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(4);

        private InnerBrLookup() {
        }

        static {
            sKeys.put(0, "_all");
            sKeys.put(1, "headerName");
            sKeys.put(2, "item");
            sKeys.put(3, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(DataBinderMapperImpl.LAYOUT_LAYOUTHEADERGENERIC);

        private InnerLayoutIdLookup() {
        }

        static {
            sKeys.put("layout/activity_add_customer_0", Integer.valueOf(R.layout.activity_add_customer));
            sKeys.put("layout/activity_add_visit_0", Integer.valueOf(R.layout.activity_add_visit));
            sKeys.put("layout/activity_all_products_0", Integer.valueOf(R.layout.activity_all_products));
            sKeys.put("layout/activity_chat_0", Integer.valueOf(R.layout.activity_chat));
            sKeys.put("layout/activity_check_in_0", Integer.valueOf(R.layout.activity_check_in));
            sKeys.put("layout/activity_complete_visit_0", Integer.valueOf(R.layout.activity_complete_visit));
            sKeys.put("layout/activity_forget_password_0", Integer.valueOf(R.layout.activity_forget_password));
            sKeys.put("layout/activity_home_0", Integer.valueOf(R.layout.activity_home));
            sKeys.put("layout/activity_location_0", Integer.valueOf(R.layout.activity_location));
            sKeys.put("layout/activity_login_0", Integer.valueOf(R.layout.activity_login));
            sKeys.put("layout/activity_low_stock_0", Integer.valueOf(R.layout.activity_low_stock));
            sKeys.put("layout/activity_otpverification_0", Integer.valueOf(R.layout.activity_otpverification));
            sKeys.put("layout/activity_profile_0", Integer.valueOf(R.layout.activity_profile));
            sKeys.put("layout/activity_refill_requests_0", Integer.valueOf(R.layout.activity_refill_requests));
            sKeys.put("layout/activity_reset_password_0", Integer.valueOf(R.layout.activity_reset_password));
            sKeys.put("layout/activity_visit_adoption_0", Integer.valueOf(R.layout.activity_visit_adoption));
            sKeys.put("layout/activity_visit_details_0", Integer.valueOf(R.layout.activity_visit_details));
            sKeys.put("layout/bottom_sheet_adoption_details_fragment_0", Integer.valueOf(R.layout.bottom_sheet_adoption_details_fragment));
            sKeys.put("layout/bottom_sheet_all_products_cart_fragment_0", Integer.valueOf(R.layout.bottom_sheet_all_products_cart_fragment));
            sKeys.put("layout/bottom_sheet_customer_details_0", Integer.valueOf(R.layout.bottom_sheet_customer_details));
            sKeys.put("layout/bottom_sheet_refill_request_product_details_0", Integer.valueOf(R.layout.bottom_sheet_refill_request_product_details));
            sKeys.put("layout/dialog_action_type_filter_0", Integer.valueOf(R.layout.dialog_action_type_filter));
            sKeys.put("layout/dialog_add_home_0", Integer.valueOf(R.layout.dialog_add_home));
            sKeys.put("layout/dialog_added_by_filter_0", Integer.valueOf(R.layout.dialog_added_by_filter));
            sKeys.put("layout/dialog_area_filter_0", Integer.valueOf(R.layout.dialog_area_filter));
            sKeys.put("layout/dialog_by_area_filter_0", Integer.valueOf(R.layout.dialog_by_area_filter));
            sKeys.put("layout/dialog_customer_inventory_more_0", Integer.valueOf(R.layout.dialog_customer_inventory_more));
            sKeys.put("layout/dialog_customer_type_filter_0", Integer.valueOf(R.layout.dialog_customer_type_filter));
            sKeys.put("layout/dialog_date_range_filter_0", Integer.valueOf(R.layout.dialog_date_range_filter));
            sKeys.put("layout/dialog_distance_filter_0", Integer.valueOf(R.layout.dialog_distance_filter));
            sKeys.put("layout/dialog_filter_main_0", Integer.valueOf(R.layout.dialog_filter_main));
            sKeys.put("layout/dialog_filter_main_inventory_0", Integer.valueOf(R.layout.dialog_filter_main_inventory));
            sKeys.put("layout/dialog_filter_main_inventory_grade_0", Integer.valueOf(R.layout.dialog_filter_main_inventory_grade));
            sKeys.put("layout/dialog_filter_main_inventory_segment_0", Integer.valueOf(R.layout.dialog_filter_main_inventory_segment));
            sKeys.put("layout/dialog_filter_main_inventory_subject_0", Integer.valueOf(R.layout.dialog_filter_main_inventory_subject));
            sKeys.put("layout/dialog_invoice_share_0", Integer.valueOf(R.layout.dialog_invoice_share));
            sKeys.put("layout/dialog_job_start_0", Integer.valueOf(R.layout.dialog_job_start));
            sKeys.put("layout/dialog_last_visit_filter_0", Integer.valueOf(R.layout.dialog_last_visit_filter));
            sKeys.put("layout/dialog_performance_filter_main_0", Integer.valueOf(R.layout.dialog_performance_filter_main));
            sKeys.put("layout/dialog_priority_filter_0", Integer.valueOf(R.layout.dialog_priority_filter));
            sKeys.put("layout/dialog_recent_activty_filter_main_0", Integer.valueOf(R.layout.dialog_recent_activty_filter_main));
            sKeys.put("layout/dialog_refill_request_0", Integer.valueOf(R.layout.dialog_refill_request));
            sKeys.put("layout/dialog_request_to_edit_0", Integer.valueOf(R.layout.dialog_request_to_edit));
            sKeys.put("layout/dialog_signout_0", Integer.valueOf(R.layout.dialog_signout));
            sKeys.put("layout/fragment_approved_refill_requests_0", Integer.valueOf(R.layout.fragment_approved_refill_requests));
            sKeys.put("layout/fragment_check_in1_0", Integer.valueOf(R.layout.fragment_check_in1));
            sKeys.put("layout/fragment_check_in2_0", Integer.valueOf(R.layout.fragment_check_in2));
            sKeys.put("layout/fragment_check_in3_0", Integer.valueOf(R.layout.fragment_check_in3));
            sKeys.put("layout/fragment_complete_visit_0", Integer.valueOf(R.layout.fragment_complete_visit));
            sKeys.put("layout/fragment_customer_0", Integer.valueOf(R.layout.fragment_customer));
            sKeys.put("layout/fragment_home_0", Integer.valueOf(R.layout.fragment_home));
            sKeys.put("layout/fragment_inventory_0", Integer.valueOf(R.layout.fragment_inventory));
            sKeys.put("layout/fragment_low_stock_selection_bottom_sheet_0", Integer.valueOf(R.layout.fragment_low_stock_selection_bottom_sheet));
            sKeys.put("layout/fragment_pending_refill_requests_0", Integer.valueOf(R.layout.fragment_pending_refill_requests));
            sKeys.put("layout/fragment_received_refill_requests_0", Integer.valueOf(R.layout.fragment_received_refill_requests));
            sKeys.put("layout/fragment_visit_adoption_0", Integer.valueOf(R.layout.fragment_visit_adoption));
            sKeys.put("layout/fragment_visit_adoption1_0", Integer.valueOf(R.layout.fragment_visit_adoption1));
            sKeys.put("layout/fragment_visit_adoption2_0", Integer.valueOf(R.layout.fragment_visit_adoption2));
            sKeys.put("layout/fragment_visit_adoption_books_0", Integer.valueOf(R.layout.fragment_visit_adoption_books));
            sKeys.put("layout/fragment_visit_adoption_grades_0", Integer.valueOf(R.layout.fragment_visit_adoption_grades));
            sKeys.put("layout/fragment_visit_adoption_quantity_0", Integer.valueOf(R.layout.fragment_visit_adoption_quantity));
            sKeys.put("layout/fragment_visit_adoption_subjects_0", Integer.valueOf(R.layout.fragment_visit_adoption_subjects));
            sKeys.put("layout/fragment_visit_details_0", Integer.valueOf(R.layout.fragment_visit_details));
            sKeys.put("layout/fragment_visit_history_0", Integer.valueOf(R.layout.fragment_visit_history));
            sKeys.put("layout/fragment_visit_samples_0", Integer.valueOf(R.layout.fragment_visit_samples));
            sKeys.put("layout/item_adoption_books_0", Integer.valueOf(R.layout.item_adoption_books));
            sKeys.put("layout/item_adoption_images_0", Integer.valueOf(R.layout.item_adoption_images));
            sKeys.put("layout/item_adoption_list_0", Integer.valueOf(R.layout.item_adoption_list));
            sKeys.put("layout/item_adoption_quantities_0", Integer.valueOf(R.layout.item_adoption_quantities));
            sKeys.put("layout/item_all_products_0", Integer.valueOf(R.layout.item_all_products));
            sKeys.put("layout/item_all_products_cart_0", Integer.valueOf(R.layout.item_all_products_cart));
            sKeys.put("layout/item_all_products_cart_2_0", Integer.valueOf(R.layout.item_all_products_cart_2));
            sKeys.put("layout/item_approved_visits_list_0", Integer.valueOf(R.layout.item_approved_visits_list));
            sKeys.put("layout/item_attachment_0", Integer.valueOf(R.layout.item_attachment));
            sKeys.put("layout/item_books_list_0", Integer.valueOf(R.layout.item_books_list));
            sKeys.put("layout/item_complete_visit_0", Integer.valueOf(R.layout.item_complete_visit));
            sKeys.put("layout/item_customer_list_filter_0", Integer.valueOf(R.layout.item_customer_list_filter));
            sKeys.put("layout/item_customers_list_0", Integer.valueOf(R.layout.item_customers_list));
            sKeys.put("layout/item_customers_selection_list_0", Integer.valueOf(R.layout.item_customers_selection_list));
            sKeys.put("layout/item_grades_subjects_0", Integer.valueOf(R.layout.item_grades_subjects));
            sKeys.put("layout/item_inventory_low_stock_0", Integer.valueOf(R.layout.item_inventory_low_stock));
            sKeys.put("layout/item_inventory_products_0", Integer.valueOf(R.layout.item_inventory_products));
            sKeys.put("layout/item_low_stock_products_0", Integer.valueOf(R.layout.item_low_stock_products));
            sKeys.put("layout/item_message_received_0", Integer.valueOf(R.layout.item_message_received));
            sKeys.put("layout/item_message_sent_0", Integer.valueOf(R.layout.item_message_sent));
            sKeys.put("layout/item_past_visits_list_0", Integer.valueOf(R.layout.item_past_visits_list));
            sKeys.put("layout/item_request_list_0", Integer.valueOf(R.layout.item_request_list));
            sKeys.put("layout/item_sample_list_0", Integer.valueOf(R.layout.item_sample_list));
            sKeys.put("layout/item_segments_0", Integer.valueOf(R.layout.item_segments));
            sKeys.put("layout/item_segments_selections_0", Integer.valueOf(R.layout.item_segments_selections));
            sKeys.put("layout/item_stock_list_0", Integer.valueOf(R.layout.item_stock_list));
            sKeys.put("layout/item_visits_list_0", Integer.valueOf(R.layout.item_visits_list));
            sKeys.put("layout/layout_header_0", Integer.valueOf(R.layout.layout_header));
            sKeys.put("layout/layout_header_generic_0", Integer.valueOf(R.layout.layout_header_generic));
        }
    }
}
