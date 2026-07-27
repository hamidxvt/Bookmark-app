package com.google.android.gms.internal.measurement;

import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.webkit.ProxyConfig;
import androidx.work.WorkRequest;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import org.apache.commons.lang3.time.DateUtils;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzpe implements zzpd {
    public static final zzkm zzA;
    public static final zzkm zzB;
    public static final zzkm zzC;
    public static final zzkm zzD;
    public static final zzkm zzE;
    public static final zzkm zzF;
    public static final zzkm zzG;
    public static final zzkm zzH;
    public static final zzkm zzI;
    public static final zzkm zzJ;
    public static final zzkm zzK;
    public static final zzkm zzL;
    public static final zzkm zzM;
    public static final zzkm zzN;
    public static final zzkm zzO;
    public static final zzkm zzP;
    public static final zzkm zzQ;
    public static final zzkm zzR;
    public static final zzkm zzS;
    public static final zzkm zzT;
    public static final zzkm zzU;
    public static final zzkm zzV;
    public static final zzkm zzW;
    public static final zzkm zzX;
    public static final zzkm zzY;
    public static final zzkm zzZ;
    public static final zzkm zza;
    public static final zzkm zzaa;
    public static final zzkm zzab;
    public static final zzkm zzac;
    public static final zzkm zzad;
    public static final zzkm zzae;
    public static final zzkm zzaf;
    public static final zzkm zzag;
    public static final zzkm zzah;
    public static final zzkm zzai;
    public static final zzkm zzaj;
    public static final zzkm zzak;
    public static final zzkm zzal;
    public static final zzkm zzam;
    public static final zzkm zzan;
    public static final zzkm zzao;
    public static final zzkm zzap;
    public static final zzkm zzaq;
    public static final zzkm zzar;
    public static final zzkm zzas;
    public static final zzkm zzat;
    public static final zzkm zzau;
    public static final zzkm zzav;
    public static final zzkm zzaw;
    public static final zzkm zzb;
    public static final zzkm zzc;
    public static final zzkm zzd;
    public static final zzkm zze;
    public static final zzkm zzf;
    public static final zzkm zzg;
    public static final zzkm zzh;
    public static final zzkm zzi;
    public static final zzkm zzj;
    public static final zzkm zzk;
    public static final zzkm zzl;
    public static final zzkm zzm;
    public static final zzkm zzn;
    public static final zzkm zzo;
    public static final zzkm zzp;
    public static final zzkm zzq;
    public static final zzkm zzr;
    public static final zzkm zzs;
    public static final zzkm zzt;
    public static final zzkm zzu;
    public static final zzkm zzv;
    public static final zzkm zzw;
    public static final zzkm zzx;
    public static final zzkm zzy;
    public static final zzkm zzz;

    static {
        zzkg zzb2 = new zzkg(zzkb.zza("com.google.android.gms.measurement")).zza().zzb();
        zza = zzb2.zzc("measurement.ad_id_cache_time", WorkRequest.MIN_BACKOFF_MILLIS);
        zzb = zzb2.zzc("measurement.app_uninstalled_additional_ad_id_cache_time", DateUtils.MILLIS_PER_HOUR);
        zzc = zzb2.zzd("measurement.config.bundle_for_all_apps_on_backgrounded", true);
        zzd = zzb2.zzc("measurement.max_bundles_per_iteration", 100L);
        zze = zzb2.zzf("measurement.gbraid_campaign.campaign_params_triggering_info_update", "gclid,gbraid,gad_campaignid");
        zzf = zzb2.zzc("measurement.config.cache_time", DateUtils.MILLIS_PER_DAY);
        zzb2.zzf("measurement.log_tag", "FA");
        zzg = zzb2.zzf("measurement.config.url_authority", "app-measurement.com");
        zzh = zzb2.zzf("measurement.config.url_scheme", ProxyConfig.MATCH_HTTPS);
        zzi = zzb2.zzc("measurement.upload.debug_upload_interval", 1000L);
        zzb2.zzd("measurement.config.default_flag_values", true);
        zzj = zzb2.zzc("measurement.session.engagement_interval", DateUtils.MILLIS_PER_HOUR);
        zzk = zzb2.zzf("measurement.rb.attribution.event_params", "value|currency");
        zzl = zzb2.zzf("measurement.edpb.events_cached_in_no_data_mode", "_f,_v,_cmp");
        zzm = zzb2.zzc("measurement.upload.google_signal_max_queue_time", 605000L);
        zzn = zzb2.zzf("measurement.sgtm.google_signal.url", "https://app-measurement.com/s/d");
        zzb2.zzc("measurement.id.upload.max_conversions_per_day", 0L);
        zzo = zzb2.zzc("measurement.lifetimevalue.max_currency_tracked", 4L);
        zzp = zzb2.zzc("measurement.dma_consent.max_daily_dcu_realtime_events", 1L);
        zzq = zzb2.zzc("measurement.upload.max_event_parameter_value_length", 500L);
        zzr = zzb2.zzc("measurement.store.max_stored_events_per_app", 100000L);
        zzs = zzb2.zzc("measurement.experiment.max_ids", 50L);
        zzt = zzb2.zzc("measurement.audience.filter_result_max_count", 200L);
        zzu = zzb2.zzc("measurement.upload.max_item_scoped_custom_parameters", 27L);
        zzv = zzb2.zzc("measurement.rb.max_trigger_registrations_per_day", 1000L);
        zzw = zzb2.zzc("measurement.rb.attribution.max_trigger_uris_queried_at_once", 0L);
        zzx = zzb2.zzc("measurement.rb.attribution.client.min_ad_services_version", 7L);
        zzy = zzb2.zzc("measurement.alarm_manager.minimum_interval", DateUtils.MILLIS_PER_MINUTE);
        zzz = zzb2.zzc("measurement.upload.minimum_delay", 500L);
        zzA = zzb2.zzc("measurement.monitoring.sample_period_millis", DateUtils.MILLIS_PER_DAY);
        zzB = zzb2.zzc("measurement.rb.attribution.notify_app_delay_millis", Constant.SPLASH_TIME);
        zzC = zzb2.zzd("measurement.config.notify_trigger_uris_on_backgrounded", true);
        zzD = zzb2.zzf("measurement.rb.attribution.app_allowlist", "");
        zzE = zzb2.zzc("measurement.upload.realtime_upload_interval", WorkRequest.MIN_BACKOFF_MILLIS);
        zzF = zzb2.zzc("measurement.upload.refresh_blacklisted_config_interval", 604800000L);
        zzb2.zzc("measurement.config.cache_time.service", DateUtils.MILLIS_PER_HOUR);
        zzG = zzb2.zzc("measurement.service_client.idle_disconnect_millis", CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        zzb2.zzf("measurement.log_tag.service", "FA-SVC");
        zzH = zzb2.zzc("measurement.service_client.reconnect_millis", 1000L);
        zzb2.zzf("measurement.sgtm.app_allowlist", "*");
        zzI = zzb2.zzc("measurement.sgtm.batch.long_queuing_threshold", 240000L);
        zzJ = zzb2.zzc("measurement.sgtm.batch.retry_interval", 1800000L);
        zzK = zzb2.zzc("measurement.sgtm.batch.retry_max_count", 10L);
        zzL = zzb2.zzc("measurement.sgtm.batch.retry_max_wait", 21600000L);
        zzM = zzb2.zzf("measurement.sgtm.service_upload_apps_list", "");
        zzN = zzb2.zzf("measurement.sgtm.upload.backoff_http_codes", "404,429,503,504");
        zzO = zzb2.zzc("measurement.sgtm.upload.batches_retrieval_limit", 5L);
        zzP = zzb2.zzc("measurement.sgtm.upload.max_queued_batches", CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        zzQ = zzb2.zzc("measurement.sgtm.upload.min_delay_after_background", 600000L);
        zzR = zzb2.zzc("measurement.sgtm.upload.min_delay_after_broadcast", 1000L);
        zzS = zzb2.zzc("measurement.sgtm.upload.min_delay_after_startup", CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        zzT = zzb2.zzc("measurement.sgtm.upload.retry_interval", 600000L);
        zzU = zzb2.zzc("measurement.sgtm.upload.retry_max_wait", 21600000L);
        zzV = zzb2.zzc("measurement.upload.stale_data_deletion_interval", DateUtils.MILLIS_PER_DAY);
        zzW = zzb2.zzc("measurement.rb.attribution.max_retry_delay_seconds", 16L);
        zzX = zzb2.zzc("measurement.rb.attribution.client.min_time_after_boot_seconds", 90L);
        zzY = zzb2.zzf("measurement.rb.attribution.uri_authority", "google-analytics.com");
        zzZ = zzb2.zzc("measurement.rb.attribution.max_queue_time", 864000000L);
        zzaa = zzb2.zzf("measurement.rb.attribution.uri_path", "privacy-sandbox/register-app-conversion");
        zzab = zzb2.zzf("measurement.rb.attribution.query_parameters_to_remove", "");
        zzac = zzb2.zzf("measurement.rb.attribution.uri_scheme", ProxyConfig.MATCH_HTTPS);
        zzad = zzb2.zzc("measurement.sdk.attribution.cache.ttl", 604800000L);
        zzae = zzb2.zzc("measurement.redaction.app_instance_id.ttl", 7200000L);
        zzaf = zzb2.zzc("measurement.upload.backoff_period", 43200000L);
        zzag = zzb2.zzc("measurement.upload.initial_upload_delay_time", 15000L);
        zzah = zzb2.zzc("measurement.upload.interval", DateUtils.MILLIS_PER_HOUR);
        zzai = zzb2.zzc("measurement.upload.max_bundle_size", 65536L);
        zzaj = zzb2.zzc("measurement.upload.max_bundles", 100L);
        zzak = zzb2.zzc("measurement.upload.max_conversions_per_day", 500L);
        zzal = zzb2.zzc("measurement.upload.max_error_events_per_day", 1000L);
        zzam = zzb2.zzc("measurement.upload.max_events_per_bundle", 1000L);
        zzan = zzb2.zzc("measurement.upload.max_events_per_day", 100000L);
        zzao = zzb2.zzc("measurement.upload.max_public_events_per_day", 50000L);
        zzap = zzb2.zzc("measurement.upload.max_queue_time", 518400000L);
        zzaq = zzb2.zzc("measurement.upload.max_realtime_events_per_day", 10L);
        zzar = zzb2.zzc("measurement.upload.max_batch_size", 65536L);
        zzas = zzb2.zzc("measurement.upload.retry_count", 6L);
        zzat = zzb2.zzc("measurement.upload.retry_time", 1800000L);
        zzau = zzb2.zzf("measurement.upload.url", "https://app-measurement.com/a");
        zzav = zzb2.zzc("measurement.upload.window_interval", DateUtils.MILLIS_PER_HOUR);
        zzaw = zzb2.zzf("measurement.rb.attribution.user_properties", "_npa,npa|_fot,fot");
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzA() {
        return ((Long) zzA.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzB() {
        return ((Long) zzB.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final boolean zzC() {
        return ((Boolean) zzC.zzd()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final String zzD() {
        return (String) zzD.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzE() {
        return ((Long) zzE.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzF() {
        return ((Long) zzF.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzG() {
        return ((Long) zzG.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzH() {
        return ((Long) zzH.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzI() {
        return ((Long) zzI.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzJ() {
        return ((Long) zzJ.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzK() {
        return ((Long) zzK.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzL() {
        return ((Long) zzL.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final String zzM() {
        return (String) zzM.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final String zzN() {
        return (String) zzN.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzO() {
        return ((Long) zzO.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzP() {
        return ((Long) zzP.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzQ() {
        return ((Long) zzQ.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzR() {
        return ((Long) zzR.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzS() {
        return ((Long) zzS.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzT() {
        return ((Long) zzT.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzU() {
        return ((Long) zzU.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzV() {
        return ((Long) zzV.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzW() {
        return ((Long) zzW.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzX() {
        return ((Long) zzX.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final String zzY() {
        return (String) zzY.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzZ() {
        return ((Long) zzZ.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zza() {
        return ((Long) zza.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final String zzaa() {
        return (String) zzaa.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final String zzab() {
        return (String) zzab.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final String zzac() {
        return (String) zzac.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzad() {
        return ((Long) zzad.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzae() {
        return ((Long) zzae.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzaf() {
        return ((Long) zzaf.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzag() {
        return ((Long) zzag.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzah() {
        return ((Long) zzah.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzai() {
        return ((Long) zzai.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzaj() {
        return ((Long) zzaj.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzak() {
        return ((Long) zzak.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzal() {
        return ((Long) zzal.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzam() {
        return ((Long) zzam.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzan() {
        return ((Long) zzan.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzao() {
        return ((Long) zzao.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzap() {
        return ((Long) zzap.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzaq() {
        return ((Long) zzaq.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzar() {
        return ((Long) zzar.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzas() {
        return ((Long) zzas.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzat() {
        return ((Long) zzat.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final String zzau() {
        return (String) zzau.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzav() {
        return ((Long) zzav.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final String zzaw() {
        return (String) zzaw.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzb() {
        return ((Long) zzb.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final boolean zzc() {
        return ((Boolean) zzc.zzd()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzd() {
        return ((Long) zzd.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final String zze() {
        return (String) zze.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzf() {
        return ((Long) zzf.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final String zzg() {
        return (String) zzg.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final String zzh() {
        return (String) zzh.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzi() {
        return ((Long) zzi.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzj() {
        return ((Long) zzj.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final String zzk() {
        return (String) zzk.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final String zzl() {
        return (String) zzl.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzm() {
        return ((Long) zzm.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final String zzn() {
        return (String) zzn.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzo() {
        return ((Long) zzo.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzp() {
        return ((Long) zzp.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzq() {
        return ((Long) zzq.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzr() {
        return ((Long) zzr.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzs() {
        return ((Long) zzs.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzt() {
        return ((Long) zzt.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzu() {
        return ((Long) zzu.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzv() {
        return ((Long) zzv.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzw() {
        return ((Long) zzw.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzx() {
        return ((Long) zzx.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzy() {
        return ((Long) zzy.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final long zzz() {
        return ((Long) zzz.zzd()).longValue();
    }
}
