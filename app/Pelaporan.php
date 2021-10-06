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
            case 'delete_pelaporan':
                return self::delete_pelaporan($parameter);
                break;
        }
    }

    public function __GET__($parameter = array()) {
        switch ($parameter[1]) {
            case 'list':
                return array();
                break;
            case 'detail':
                return self::detail_pelaporan($parameter[2]);
                break;
            default:
                return self::get_my_pelaporan($parameter);
        }
    }

    private function get_my_pelaporan($parameter) {
        $Authorization = new Authorization();
        $UserData = $Authorization->readBearerToken($parameter['access_token']);

        $data = self::$query->select('lapor', array(
            'id', 'id_jenis', 'id_kecamatan', 'id_kelurahan', 'created_at'
        ))
            ->where(array(
                'lapor.uid_pegawai' => '= ?',
                'AND',
                'lapor.deleted_at' => 'IS NULL'
            ), array(
                $UserData['data']->uid
            ))
            ->execute();

        $resultList = [];
        foreach($data['response_data'] as $key => $value) {

            $resultItems = $value;

            if(intval($value['id_jenis']) === 1) {
                $resultItems['nama_jenis'] = 'Laporan Kematian';

                $mati = self::$query->select('lapor_mati', array(
                    'nik', 'nama_lengkap', 'tempat_lahir', 'tanggal_lahir', 'tempat_meninggal', 'tanggal_meninggal', 'jam_meninggal', 'nama_keluarga', 'no_handphone_keluarga'
                ))
                    ->where(array(
                        'lapor_mati.id_lapor' => '= ?'
                    ), array(
                        $value['id']
                    ))
                    ->execute();

                $dataMati = $mati['response_data'];
                if (count($dataMati) > 0) {
                    $resultItems['nik'] = $dataMati[0]['nik'];
                    $resultItems['nama'] = $dataMati[0]['nama_lengkap'];
                    $resultItems['detail'] = $dataMati;

                    $resultList[] = $resultItems;
                }


            } else if(intval($value['id_jenis']) === 2) {
                $resultItems['nama_jenis'] = 'Laporan Kelahiran';

                $lahir = self::$query->select('lapor_lahir', array(
                    'nik_ortu', 'nama_ortu', 'nama_anak', 'tanggal_lahir', 'tempat_lahir', 'alamat'
                ))
                    ->where(array(
                        'lapor_lahir.id_lapor' => '= ?'
                    ), array(
                        $value['id']
                    ))
                    ->execute();

                $dataLahir = $lahir['response_data'];
                if (count($dataLahir) > 0) {
                    $resultItems['nik'] = $dataLahir[0]['nik_ortu'];
                    $resultItems['nama'] = $dataLahir[0]['nama_ortu'];
                    $resultItems['detail'] = $dataLahir;

                    $resultList[] = $resultItems;
                }

            } else if(intval($value['id_jenis']) === 3) {
                $resultItems['nama_jenis'] = 'Laporan Pindah';

                $pindah = self::$query->select('lapor_pindah', array(
                    'nik', 'nama', 'status_keluarga', 'jenis_pindah', 'alamat'
                ))
                    ->where(array(
                        'lapor_pindah.id_lapor' => '= ?'
                    ), array(
                        $value['id']
                    ))
                    ->execute();

                $dataPindah = $pindah['response_data'];
                if (count($dataPindah) > 0) {
                    $resultItems['nik'] = $dataPindah[0]['nik'];
                    $resultItems['nama'] = $dataPindah[0]['nama'];
                    $resultItems['detail'] = $dataPindah;

                    $resultList[] = $resultItems;
                }
            }
        }

        $data['response_result'] = count($resultList);
        $data['response_data'] = $resultList;

        return $data;
    }

    private function detail_pelaporan($parameter) {
        $Authorization = new Authorization();
        $UserData = $Authorization->readBearerToken($parameter['access_token']);

        $Authorization = new Authorization();
        $UserData = $Authorization->readBearerToken($parameter['access_token']);

        $data = self::$query->select('lapor', array(
            'id', 'id_jenis', 'id_kecamatan', 'id_kelurahan', 'created_at'
        ))
            ->where(array(
                'lapor.id' => '= ?',
                'AND',
                'lapor.uid_pegawai' => '= ?',
                'AND',
                'lapor.deleted_at' => 'IS NULL'
            ), array(
                $parameter,
                $UserData['data']->uid
            ))
            ->execute();
        foreach($data['response_data'] as $key => $value) {
            $data['response_data'][$key]['tgl_submit'] = date('d F Y', strtotime($value['created_at']));
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
                foreach ($mati['response_data'] as $dK => $dV) {
                    foreach ($dV as $dKK => $dKV) {
                        $data['response_data'][$key][$dKK] = $dKV;
                    }
                }
                //$data['response_data'][$key]['detail'] = $mati['response_data'];
            } else if(intval($value['id_jenis']) === 2) {
                $data['response_data'][$key]['nama_jenis'] = 'Laporan Kelahiran';
                $lahir = self::$query->select('lapor_lahir', array(
                    'nik_ortu', 'nama_ortu', 'nama_anak', 'tanggal_lahir', 'tempat_lahir', 'alamat'
                ))
                    ->where(array(
                        'lapor_lahir.id_lapor' => '= ?'
                    ), array(
                        $value['id']
                    ))
                    ->execute();
                foreach ($lahir['response_data'] as $dK => $dV) {
                    foreach ($dV as $dKK => $dKV) {
                        $data['response_data'][$key][$dKK] = $dKV;
                    }
                }
                //$data['response_data'][$key]['detail'] = $lahir['response_data'];
            } else if(intval($value['id_jenis']) === 3) {
                $data['response_data'][$key]['nama_jenis'] = 'Laporan Pindah';
                $pindah = self::$query->select('lapor_pindah', array(
                    'nik', 'nama', 'status_keluarga', 'jenis_pindah', 'alamat'
                ))
                    ->where(array(
                        'lapor_pindah.id_lapor' => '= ?'
                    ), array(
                        $value['id']
                    ))
                    ->execute();
                foreach ($pindah['response_data'] as $dK => $dV) {
                    foreach ($dV as $dKK => $dKV) {
                        $data['response_data'][$key][$dKK] = $dKV;
                    }
                }
                //$data['response_data'][$key]['detail'] = $pindah['response_data'];
            }
        }

        return $data;
    }

    /*private function detail_pelaporan($parameter) {
        $Authorization = new Authorization();
        $UserData = $Authorization->readBearerToken($parameter['access_token']);

        $Authorization = new Authorization();
        $UserData = $Authorization->readBearerToken($parameter['access_token']);

        $data = self::$query->select('lapor', array(
            'id', 'id_jenis', 'id_kecamatan', 'id_kelurahan', 'created_at'
        ))
            ->where(array(
                'lapor.id' => '= ?',
                'AND',
                'lapor.uid_pegawai' => '= ?',
                'AND',
                'lapor.deleted_at' => 'IS NULL'
            ), array(
                $parameter,
                $UserData['data']->uid
            ))
            ->execute();
        foreach($data['response_data'] as $key => $value) {
            $data['response_data'][$key]['tgl_submit'] = date('d F Y', strtotime($value['created_at']));
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
                foreach ($mati['response_data'] as $dK => $dV) {
                    foreach ($dV as $dKK => $dKV) {
                        $data['response_data'][$key][$dKK] = $dKV;
                    }
                }
                //$data['response_data'][$key]['detail'] = $mati['response_data'];
            } else if(intval($value['id_jenis']) === 2) {
                $data['response_data'][$key]['nama_jenis'] = 'Laporan Kelahiran';
                $lahir = self::$query->select('lapor_lahir', array(
                    'nik_ortu', 'nama_ortu', 'nama_anak', 'tanggal_lahir', 'tempat_lahir', 'alamat'
                ))
                    ->where(array(
                        'lapor_lahir.id_lapor' => '= ?'
                    ), array(
                        $value['id']
                    ))
                    ->execute();
                foreach ($lahir['response_data'] as $dK => $dV) {
                    foreach ($dV as $dKK => $dKV) {
                        $data['response_data'][$key][$dKK] = $dKV;
                    }
                }
                //$data['response_data'][$key]['detail'] = $lahir['response_data'];
            } else if(intval($value['id_jenis']) === 3) {
                $data['response_data'][$key]['nama_jenis'] = 'Laporan Pindah';
                $pindah = self::$query->select('lapor_pindah', array(
                    'nik', 'nama', 'status_keluarga', 'jenis_pindah', 'alamat'
                ))
                    ->where(array(
                        'lapor_pindah.id_lapor' => '= ?'
                    ), array(
                        $value['id']
                    ))
                    ->execute();
                foreach ($pindah['response_data'] as $dK => $dV) {
                    foreach ($dV as $dKK => $dKV) {
                        $data['response_data'][$key][$dKK] = $dKV;
                    }
                }
                //$data['response_data'][$key]['detail'] = $pindah['response_data'];
            }
        }

        return $data;
    }*/

    private function delete_pelaporan($parameter) {
        $Authorization = new Authorization();
        $UserData = $Authorization->readBearerToken($parameter['access_token']);

        $delete = self::$query->delete('lapor')
            ->where(array(
                'lapor.id' => '= ?',
                'AND',
                'lapor.uid_pegawai' => '= ?'
            ), array(
                $parameter['id'], $UserData['data']->uid
            ))
            ->execute();
        return $delete;
    }

    private function tambah_pelaporan($parameter) {
        $Authorization = new Authorization();
        $UserData = $Authorization->readBearerToken($parameter['access_token']);

        $Lapor = self::$query->insert('lapor', array(
            'created_at' => parent::format_date(),
            'updated_at' => parent::format_date(),
            'uid_pegawai' => $UserData['data']->uid,
            'id_kecamatan' => intval($UserData['data']->kecamatan),
            'id_kelurahan' => intval($UserData['data']->kelurahan),
            'id_lingkungan' => intval($UserData['data']->lingkungan),
            'id_faskes' => intval($UserData['data']->faskes),
            'id_jenis' => intval($parameter['jenis']),
            'kode' => strtoupper(parent::generatePassword(6))
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
            } else if(intval($parameter['jenis']) === 2) {
                $LaporLahir = self::$query->insert('lapor_lahir', array(
                    'nik_ortu' => $parameter['nik_ortu'],
                    'nama_ortu' => $parameter['nama_ortu'],
                    'tempat_lahir' => $parameter['tempat_lahir'],
                    'tanggal_lahir' => $parameter['tanggal_lahir'],
                    'nama_anak' => $parameter['nama_anak'],
                    'alamat' => $parameter['alamat'],
                    'created_at' => parent::format_date(),
                    'updated_at' => parent::format_date(),
                    'id_lapor' => $Lapor['response_unique']
                ))
                    ->returning('id')
                    ->execute();

                if($LaporLahir['response_result'] > 0) {
                    $LogLaporLahir = parent::log(array(
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
                            $LaporLahir['response_unique'],
                            $UserData['data']->uid,
                            'lapor_lahir',
                            'I',
                            parent::format_date(),
                            'N',
                            $UserData['data']->log_id
                        ),
                        'class' => __CLASS__
                    ));
                }

                $Lapor['detail'] = $LaporLahir;
            } else if(intval($parameter['jenis']) === 3) {
                $LaporPindah = self::$query->insert('lapor_pindah', array(
                    'nik' => $parameter['nik'],
                    'nama' => $parameter['nama'],
                    'status_keluarga' => $parameter['status_keluarga'],
                    'jenis_pindah' => $parameter['jenis_pindah'],
                    'alamat' => $parameter['alamat'],
                    'created_at' => parent::format_date(),
                    'updated_at' => parent::format_date(),
                    'id_lapor' => $Lapor['response_unique']
                ))
                    ->returning('id')
                    ->execute();

                if($LaporPindah['response_result'] > 0) {
                    $LogLaporPindah = parent::log(array(
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
                            $LaporPindah['response_unique'],
                            $UserData['data']->uid,
                            'lapor_pindah',
                            'I',
                            parent::format_date(),
                            'N',
                            $UserData['data']->log_id
                        ),
                        'class' => __CLASS__
                    ));
                }

                $Lapor['detail'] = $LaporPindah;
            }
        }

        return $Lapor;

        /*return array(
            'response_result' => $Lapor['response_result'],
            'response_message' => $Lapor['response_message']
        );*/
    }
}

?>