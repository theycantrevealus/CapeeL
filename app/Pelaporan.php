<?php

namespace PondokCoder;

use PondokCoder\Authorization as Authorization;
use PondokCoder\Query as Query;
use PondokCoder\QueryException as QueryException;
use PondokCoder\Utility as Utility;


class Pelaporan extends Utility {
    static $pdo;
    static $query;

    protected static function getConn(){
        return self::$pdo;
    }

    public function __construct($connection) {
        self::$pdo = $connection;
        self::$query = new Query(self::$pdo);
    }

    public function __POST__($parameter = array()) {
        switch ($parameter['request']) {
            case 'tambah_pelaporan':
                return self::tambah_pelaporan($parameter);
                break;
        }
    }

    public function __GET__($parameter = array()) {
        switch ($parameter[1]) {
            case 'list':
                return array();
                break;
            default:
                return self::get_my_pelaporan($parameter);
        }
    }

    private function get_my_pelaporan($parameter) {
        $Authorization = new Authorization();
        $UserData = $Authorization->readBearerToken($parameter['access_token']);

        $data = self::$query->select('lapor', array(
            'id', 'id_jenis', 'id_kecamatan', 'id_kelurahan'
        ))
            ->where(array(
                'lapor.uid_pegawai' => '= ?',
                'AND',
                'lapor.deleted_at' => 'IS NULL'
            ), array(
                $UserData['data']->uid
            ))
            ->execute();
        foreach($data['response_data'] as $key => $value) {
            if(intval($value['id_jenis']) === 1) {
                $data['response_data'][$key]['nama_jenis'] = 'Laporan Kematian';
                $mati = self::$query->select('lapor_mati', array(
                    'nik', 'nama_lengkap', 'tempat_lahir', 'tanggal_lahir', 'tempat_meninggal', 'tanggal_meninggal', 'jam_meninggal', 'nama_keluarga', 'no_handphone_keluarga'
                ))
                    ->where(array(
                        'lapor_mati.id_lapor' => '= ?'
                    ), array(
                        $value['id']
                    ))
                    ->execute();
                $data['response_data'][$key]['detail'] = $mati['response_data'];
            }
        }


        return $data;
    }

    private function tambah_pelaporan($parameter) {
        $Authorization = new Authorization();
        $UserData = $Authorization->readBearerToken($parameter['access_token']);

        $Lapor = self::$query->insert('lapor', array(
            'created_at' => parent::format_date(),
            'updated_at' => parent::format_date(),
            'uid_pegawai' => $UserData['data']->uid,
            'id_kecamatan' => $parameter['kecamatan'],
            'id_kelurahan' => $parameter['kelurahan'],
            'id_jenis' => $parameter['jenis']
        ))
            ->returning('id')
            ->execute();

        if($Lapor['response_result'] > 0) {
            //Log
            $LogLapor = parent::log(array(
                'type' => 'activity',
                'column' => array(
                    'unique_target',
                    'user_uid',
                    'table_name',
                    'action',
                    'logged_at',
                    'status',
                    'login_id'
                ),
                'value' => array(
                    $Lapor['response_unique'],
                    $UserData['data']->uid,
                    'lapor',
                    'I',
                    parent::format_date(),
                    'N',
                    $UserData['data']->log_id
                ),
                'class' => __CLASS__
            ));

            if(intval($parameter['jenis']) === 1) {
                $LaporMati = self::$query->insert('lapor_mati', array(
                    'nik' => $parameter['nik'],
                    'nama_lengkap' => $parameter['nama_lengkap'],
                    'tempat_lahir' => $parameter['tempat_lahir'],
                    'tanggal_lahir' => $parameter['tanggal_lahir'],
                    'tempat_meninggal' => $parameter['tempat_meninggal'],
                    'tanggal_meninggal' => $parameter['tanggal_meninggal'],
                    'jam_meninggal' => $parameter['jam_meninggal'],
                    'nama_keluarga' => $parameter['nama_keluarga'],
                    'no_handphone_keluarga' => $parameter['no_handphone_keluarga'],
                    'id_lapor' => $Lapor['response_unique']
                ))
                    ->returning('id')
                    ->execute();

                if($LaporMati['response_result'] > 0) {
                    $LogLaporMati = parent::log(array(
                        'type' => 'activity',
                        'column' => array(
                            'unique_target',
                            'user_uid',
                            'table_name',
                            'action',
                            'logged_at',
                            'status',
                            'login_id'
                        ),
                        'value' => array(
                            $LaporMati['response_unique'],
                            $UserData['data']->uid,
                            'lapor_mati',
                            'I',
                            parent::format_date(),
                            'N',
                            $UserData['data']->log_id
                        ),
                        'class' => __CLASS__
                    ));
                }

                $Lapor['detail'] = $LaporMati;
            }
        }
        return $Lapor;
    }
}

?>