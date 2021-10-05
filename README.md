# CapeeL
CapeeL API Only


# API Documentation
1. Login

   **Request**
   ```
   curl --request POST \
   --url http://127.0.0.1/CapeeL/Pegawai \
   --header 'Content-Type: application/x-www-form-urlencoded' \
   --cookie PHPSESSID=63gclihstfp1bgum9pctmg3udm \
   --data request=login \
   --data username=tanaka \
   --data password=123
   ```

   **Response**
   ```
   {
      "response_result": 1,
      "response_message": "Login berhasil",
      "response_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjFcL3NpbXJzdjJcL2NsaWVudCIsImlhdCI6MTYzMjgxNTQwOCwibmJmIjoxNjMyODE1NDE4LCJleHAiOjE2MzI4MTU0MzgsImF1ZCI6InVzZXJzX2xpYnJhcnkiLCJkYXRhIjp7InVpZCI6IjgxMTM2NTJkLTRjYjctZTg1MC1kNDg3LTI4MWExNzYyMDQyYSIsImZvdG8iOiIiLCJwYXNzd29yZCI6IiQyeSQxMCRGYTlvWU95Yi53dElaMXBMbXN6NGNlRnJtMHowT3d2ZlI2elM3Tzg3bGVVakhuem1VQm94QyIsImVtYWlsIjoidGhleWNhbnRyZXZlYWx1c0BnbWFpbC5jb20iLCJuYW1hIjoiSGVuZHJ5IFRhbmFrYSIsIm5vX2hhbmRwaG9uZSI6IjA4NTI2MTUxMDIwMiIsImxvZ19pZCI6IjMwIn19.jWjDIaXEzYDvZBQbAb10AgzMk_e5JiRmIx67PRQtvxs",
      "response_data": {
         "uid": "8113652d-4cb7-e850-d487-281a1762042a",
         "username": "tanaka",
         "nama": "Hendry Tanaka",
         "email": "theycantrevealus@gmail.com",
         "no_handphone": "085261510202",
         "foto": "",
         "is_login": "N",
         "last_login": "2021-09-16 15:27:58.224389",
         "id_level": null,
         "created_at": "2021-09-16 15:27:58.224389",
         "updated_at": "2021-09-16 15:27:58.224389",
         "deleted_at": null,
         "id": 1
      }
   }
   ```

2. Pelaporan Mati

   **Request**
   ```
   curl --request POST \
   --url http://127.0.0.1/CapeeL/Pelaporan \
   --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjFcL3NpbXJzdjJcL2NsaWVudCIsImlhdCI6MTYzMTc4MzQzOSwibmJmIjoxNjMxNzgzNDQ5LCJleHAiOjE2MzE3ODM0NjksImF1ZCI6InVzZXJzX2xpYnJhcnkiLCJkYXRhIjp7InVpZCI6IjgxMTM2NTJkLTRjYjctZTg1MC1kNDg3LTI4MWExNzYyMDQyYSIsImZvdG8iOiIiLCJwYXNzd29yZCI6IiQyeSQxMCR4ZHdBUjlycFltU2Z6S09ZeWZKa2N1T1VrbXhxSS50YjAza2RKcEU0MUhicGlIbmR3RUhEUyIsImVtYWlsIjoiIiwibmFtYSI6IlRhbmFrYSIsIm5vX2hhbmRwaG9uZSI6IiIsImxvZ19pZCI6IjcifX0.Xx1LxPngZCx2cjbp8RTwekmyP_s3LKEPiz3BJWnWIkg' \
   --header 'Content-Type: multipart/form-data; boundary=---011000010111000001101001' \
   --cookie PHPSESSID=63gclihstfp1bgum9pctmg3udm \
   --form request=tambah_pelaporan \
   --form nik=2232312 \
   --form 'nama_lengkap=Li Zuan Da' \
   --form tempat_lahir=Medan \
   --form tanggal_lahir=10-10-1990 \
   --form tempat_meninggal=Medan \
   --form tanggal_meninggal=16-09-2021 \
   --form jam_meninggal=10:10:10 \
   --form nama_keluarga=Paijo \
   --form no_handphone_keluarga=085300290191 \
   --form kecamatan=1 \
   --form kelurahan=1 \
   --form jenis=1
   ```

   **Response**
   ```
   {
      "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjFcL3NpbXJzdjJcL2NsaWVudCIsImlhdCI6MTYzMTc4MzcyOSwibmJmIjoxNjMxNzgzNDQ5LCJleHAiOjE2MzE3ODM3ODksImF1ZCI6InVzZXJzX2xpYnJhcnkiLCJkYXRhIjp7InVpZCI6IjgxMTM2NTJkLTRjYjctZTg1MC1kNDg3LTI4MWExNzYyMDQyYSIsImZvdG8iOiIiLCJwYXNzd29yZCI6IiQyeSQxMCR4ZHdBUjlycFltU2Z6S09ZeWZKa2N1T1VrbXhxSS50YjAza2RKcEU0MUhicGlIbmR3RUhEUyIsImVtYWlsIjoiIiwibmFtYSI6IlRhbmFrYSIsIm5vX2hhbmRwaG9uZSI6IiIsImxvZ19pZCI6IjcifX0.iIUnKB8UNtTgYkuhRXnBAxGergKp-PfOPVa_jwHJj3E",
      "response_package": {
         "response_query": "INSERT INTO lapor (created_at,updated_at,uid_pegawai,id_kecamatan,id_kelurahan,id_jenis) VALUES (?,?,?,?,?,?) RETURNING id",
         "response_values": [
            "2021-09-16 16:15:29",
            "2021-09-16 16:15:29",
            "8113652d-4cb7-e850-d487-281a1762042a",
            "1",
            "1",
            "1"
         ],
         "response_unique": 5,
         "response_message": "Data berhasil ditambahkan",
         "response_result": 1,
         "detail": {
            "response_query": "INSERT INTO lapor_mati (nik,nama_lengkap,tempat_lahir,tanggal_lahir,tempat_meninggal,tanggal_meninggal,jam_meninggal,nama_keluarga,no_handphone_keluarga,id_lapor) VALUES (?,?,?,?,?,?,?,?,?,?) RETURNING id",
            "response_values": [
               "2232312",
               "Li Zuan Da",
               "Medan",
               "10-10-1990",
               "Medan",
               "16-09-2021",
               "10:10:10",
               "Paijo",
               "085300290191",
               5
            ],
            "response_unique": 2,
            "response_message": "Data berhasil ditambahkan",
            "response_result": 1
         }
      },
      "license": "-----BEGIN PUBLIC KEY-----\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDXVQmq3+UqbhC3rLCXSEu\/\/miV\nFXhkr+zoK17NTfA9VbdVT95Ag+CLi8hEAnkffpPEacLAIoVjOgtzT4wlWTpkUHCR\nLlVqw6mjJsqF4EWH4b4N\/eJ+7S0O+vAJi7cxscOaU6zs9Dm+lPNvN4AmRi05xOHW\nDhZ8i8+VWEP\/azAO1wIDAQAB\n-----END PUBLIC KEY-----\n"
   }
   ```

3. Pelaporan Lahir

   **Request**
   ```
   curl --request POST \
   --url http://127.0.0.1/CapeeL/Pelaporan \
   --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjFcL3NpbXJzdjJcL2NsaWVudCIsImlhdCI6MTYzMjgxNTQwOCwibmJmIjoxNjMyODE1NDE4LCJleHAiOjE2MzI4MTU0MzgsImF1ZCI6InVzZXJzX2xpYnJhcnkiLCJkYXRhIjp7InVpZCI6IjgxMTM2NTJkLTRjYjctZTg1MC1kNDg3LTI4MWExNzYyMDQyYSIsImZvdG8iOiIiLCJwYXNzd29yZCI6IiQyeSQxMCRGYTlvWU95Yi53dElaMXBMbXN6NGNlRnJtMHowT3d2ZlI2elM3Tzg3bGVVakhuem1VQm94QyIsImVtYWlsIjoidGhleWNhbnRyZXZlYWx1c0BnbWFpbC5jb20iLCJuYW1hIjoiSGVuZHJ5IFRhbmFrYSIsIm5vX2hhbmRwaG9uZSI6IjA4NTI2MTUxMDIwMiIsImxvZ19pZCI6IjMwIn19.jWjDIaXEzYDvZBQbAb10AgzMk_e5JiRmIx67PRQtvxs' \
   --header 'Content-Type: multipart/form-data; boundary=---011000010111000001101001' \
   --cookie PHPSESSID=63gclihstfp1bgum9pctmg3udm \
   --form request=tambah_pelaporan \
   --form nik_ortu=2232312 \
   --form 'nama_ortu=Li Zuan Da' \
   --form tempat_lahir=Medan \
   --form tanggal_lahir=10-10-2021 \
   --form nama_anak=Sukiman \
   --form 'alamat=Medan Petisah' \
   --form jam_meninggal=10:10:10 \
   --form jenis=2 \
   --form kecamatan=1 \
   --form kelurahan=1
   ```

   **Response**
   ```
   {
      "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjFcL3NpbXJzdjJcL2NsaWVudCIsImlhdCI6MTYzMjgxNTU1MCwibmJmIjoxNjMyODE1NDE4LCJleHAiOjE2MzI4MTU2MTAsImF1ZCI6InVzZXJzX2xpYnJhcnkiLCJkYXRhIjp7InVpZCI6IjgxMTM2NTJkLTRjYjctZTg1MC1kNDg3LTI4MWExNzYyMDQyYSIsImZvdG8iOiIiLCJwYXNzd29yZCI6IiQyeSQxMCRGYTlvWU95Yi53dElaMXBMbXN6NGNlRnJtMHowT3d2ZlI2elM3Tzg3bGVVakhuem1VQm94QyIsImVtYWlsIjoidGhleWNhbnRyZXZlYWx1c0BnbWFpbC5jb20iLCJuYW1hIjoiSGVuZHJ5IFRhbmFrYSIsIm5vX2hhbmRwaG9uZSI6IjA4NTI2MTUxMDIwMiIsImxvZ19pZCI6IjMwIn19.dDI7NWQ8sWAo2HS-rIWJ0bkndeW93PUmDrMuh9_T3cc",
      "response_package": {
         "response_query": "INSERT INTO lapor (created_at,updated_at,uid_pegawai,id_kecamatan,id_kelurahan,id_jenis) VALUES (?,?,?,?,?,?) RETURNING id",
         "response_values": [
            "2021-09-28 14:52:30",
            "2021-09-28 14:52:30",
            "8113652d-4cb7-e850-d487-281a1762042a",
            "1",
            "1",
            "2"
         ],
         "response_unique": 7,
         "response_message": "Data berhasil ditambahkan",
         "response_result": 1,
         "detail": {
            "response_query": "INSERT INTO lapor_lahir (nik_ortu,nama_ortu,tempat_lahir,tanggal_lahir,nama_anak,alamat,created_at,updated_at,id_lapor) VALUES (?,?,?,?,?,?,?,?,?) RETURNING id",
            "response_values": [
               "2232312",
               "Li Zuan Da",
               "Medan",
               "10-10-2021",
               "Sukiman",
               "Medan Petisah",
               "2021-09-28 14:52:30",
               "2021-09-28 14:52:30",
               7
            ],
            "response_unique": 1,
            "response_message": "Data berhasil ditambahkan",
            "response_result": 1
         }
      },
      "license": "-----BEGIN PUBLIC KEY-----\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDXVQmq3+UqbhC3rLCXSEu\/\/miV\nFXhkr+zoK17NTfA9VbdVT95Ag+CLi8hEAnkffpPEacLAIoVjOgtzT4wlWTpkUHCR\nLlVqw6mjJsqF4EWH4b4N\/eJ+7S0O+vAJi7cxscOaU6zs9Dm+lPNvN4AmRi05xOHW\nDhZ8i8+VWEP\/azAO1wIDAQAB\n-----END PUBLIC KEY-----\n"
   }
   ```
   
4. Pelaporan Pindah

   **Request**
   ```
   curl --request POST \
   --url http://127.0.0.1/CapeeL/Pelaporan \
   --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjFcL3NpbXJzdjJcL2NsaWVudCIsImlhdCI6MTYzMjgxNTQwOCwibmJmIjoxNjMyODE1NDE4LCJleHAiOjE2MzI4MTU0MzgsImF1ZCI6InVzZXJzX2xpYnJhcnkiLCJkYXRhIjp7InVpZCI6IjgxMTM2NTJkLTRjYjctZTg1MC1kNDg3LTI4MWExNzYyMDQyYSIsImZvdG8iOiIiLCJwYXNzd29yZCI6IiQyeSQxMCRGYTlvWU95Yi53dElaMXBMbXN6NGNlRnJtMHowT3d2ZlI2elM3Tzg3bGVVakhuem1VQm94QyIsImVtYWlsIjoidGhleWNhbnRyZXZlYWx1c0BnbWFpbC5jb20iLCJuYW1hIjoiSGVuZHJ5IFRhbmFrYSIsIm5vX2hhbmRwaG9uZSI6IjA4NTI2MTUxMDIwMiIsImxvZ19pZCI6IjMwIn19.jWjDIaXEzYDvZBQbAb10AgzMk_e5JiRmIx67PRQtvxs' \
   --header 'Content-Type: multipart/form-data; boundary=---011000010111000001101001' \
   --cookie PHPSESSID=63gclihstfp1bgum9pctmg3udm \
   --form request=tambah_pelaporan \
   --form nik=2232312 \
   --form 'nama=Li Zuan Da' \
   --form 'alamat=Medan Petisah' \
   --form jenis_pindah=SEMENTARA \
   --form jenis=3 \
   --form kecamatan=1 \
   --form kelurahan=1 \
   --form status_keluarga=PEMBANTU
   ```

   **Response**
   ```
   {
      "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjFcL3NpbXJzdjJcL2NsaWVudCIsImlhdCI6MTYzMjgxNjAyMSwibmJmIjoxNjMyODE1NDE4LCJleHAiOjE2MzI4MTYwODEsImF1ZCI6InVzZXJzX2xpYnJhcnkiLCJkYXRhIjp7InVpZCI6IjgxMTM2NTJkLTRjYjctZTg1MC1kNDg3LTI4MWExNzYyMDQyYSIsImZvdG8iOiIiLCJwYXNzd29yZCI6IiQyeSQxMCRGYTlvWU95Yi53dElaMXBMbXN6NGNlRnJtMHowT3d2ZlI2elM3Tzg3bGVVakhuem1VQm94QyIsImVtYWlsIjoidGhleWNhbnRyZXZlYWx1c0BnbWFpbC5jb20iLCJuYW1hIjoiSGVuZHJ5IFRhbmFrYSIsIm5vX2hhbmRwaG9uZSI6IjA4NTI2MTUxMDIwMiIsImxvZ19pZCI6IjMwIn19.22-VZY0OF9F_salFpeYRq3Gx-s7nKsizK-CKTBFYXLk",
      "response_package": {
         "response_query": "INSERT INTO lapor (created_at,updated_at,uid_pegawai,id_kecamatan,id_kelurahan,id_jenis) VALUES (?,?,?,?,?,?) RETURNING id",
         "response_values": [
            "2021-09-28 15:00:21",
            "2021-09-28 15:00:21",
            "8113652d-4cb7-e850-d487-281a1762042a",
            "1",
            "1",
            "3"
         ],
         "response_unique": 11,
         "response_message": "Data berhasil ditambahkan",
         "response_result": 1,
         "detail": {
            "response_query": "INSERT INTO lapor_pindah (nik,nama,status_keluarga,jenis_pindah,alamat,created_at,updated_at,id_lapor) VALUES (?,?,?,?,?,?,?,?) RETURNING id",
            "response_values": [
               "2232312",
               "Li Zuan Da",
               "PEMBANTU",
               "SEMENTARA",
               "Medan Petisah",
               "2021-09-28 15:00:21",
               "2021-09-28 15:00:21",
               11
            ],
            "response_unique": 1,
            "response_message": "Data berhasil ditambahkan",
            "response_result": 1
         }
      },
      "license": "-----BEGIN PUBLIC KEY-----\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDXVQmq3+UqbhC3rLCXSEu\/\/miV\nFXhkr+zoK17NTfA9VbdVT95Ag+CLi8hEAnkffpPEacLAIoVjOgtzT4wlWTpkUHCR\nLlVqw6mjJsqF4EWH4b4N\/eJ+7S0O+vAJi7cxscOaU6zs9Dm+lPNvN4AmRi05xOHW\nDhZ8i8+VWEP\/azAO1wIDAQAB\n-----END PUBLIC KEY-----\n"
   }
   ```
5. Pelaporan History

   **Request**
   ```
   curl --request GET \
   --url http://127.0.0.1/CapeeL/Pelaporan \
   --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjFcL3NpbXJzdjJcL2NsaWVudCIsImlhdCI6MTYzMjgxNTQwOCwibmJmIjoxNjMyODE1NDE4LCJleHAiOjE2MzI4MTU0MzgsImF1ZCI6InVzZXJzX2xpYnJhcnkiLCJkYXRhIjp7InVpZCI6IjgxMTM2NTJkLTRjYjctZTg1MC1kNDg3LTI4MWExNzYyMDQyYSIsImZvdG8iOiIiLCJwYXNzd29yZCI6IiQyeSQxMCRGYTlvWU95Yi53dElaMXBMbXN6NGNlRnJtMHowT3d2ZlI2elM3Tzg3bGVVakhuem1VQm94QyIsImVtYWlsIjoidGhleWNhbnRyZXZlYWx1c0BnbWFpbC5jb20iLCJuYW1hIjoiSGVuZHJ5IFRhbmFrYSIsIm5vX2hhbmRwaG9uZSI6IjA4NTI2MTUxMDIwMiIsImxvZ19pZCI6IjMwIn19.jWjDIaXEzYDvZBQbAb10AgzMk_e5JiRmIx67PRQtvxs' \
   --cookie PHPSESSID=63gclihstfp1bgum9pctmg3udm
   ```
   
   **Response**
   ```
   {
      "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjFcL3NpbXJzdjJcL2NsaWVudCIsImlhdCI6MTYzMjgxNjI4OSwibmJmIjoxNjMyODE1NDE4LCJleHAiOjE2MzI4MTYzNDksImF1ZCI6InVzZXJzX2xpYnJhcnkiLCJkYXRhIjp7InVpZCI6IjgxMTM2NTJkLTRjYjctZTg1MC1kNDg3LTI4MWExNzYyMDQyYSIsImZvdG8iOiIiLCJwYXNzd29yZCI6IiQyeSQxMCRGYTlvWU95Yi53dElaMXBMbXN6NGNlRnJtMHowT3d2ZlI2elM3Tzg3bGVVakhuem1VQm94QyIsImVtYWlsIjoidGhleWNhbnRyZXZlYWx1c0BnbWFpbC5jb20iLCJuYW1hIjoiSGVuZHJ5IFRhbmFrYSIsIm5vX2hhbmRwaG9uZSI6IjA4NTI2MTUxMDIwMiIsImxvZ19pZCI6IjMwIn19.zzdq_pYRHTdKG11qRC2ADnYkO0CYhf8jq6o_JRfwfvw",
      "response_package": {
         "response_query": "SELECT lapor.id,lapor.id_jenis,lapor.id_kecamatan,lapor.id_kelurahan FROM lapor WHERE lapor.uid_pegawai = ? AND lapor.deleted_at IS NULL",
         "response_values": [
            "8113652d-4cb7-e850-d487-281a1762042a"
         ],
         "response_data": [
            {
               "id": 1,
               "id_jenis": 1,
               "id_kecamatan": 1,
               "id_kelurahan": 1,
               "nama_jenis": "Laporan Kematian",
               "detail": [
                  {
                     "nik": "2232312",
                     "nama_lengkap": "Li Zuan Da",
                     "tempat_lahir": "Medan",
                     "tanggal_lahir": "1990-10-10",
                     "tempat_meninggal": "Medan",
                     "tanggal_meninggal": "2021-09-16",
                     "jam_meninggal": "10:10:10",
                     "nama_keluarga": "Paijo",
                     "no_handphone_keluarga": "085300290191"
                  }
               ]
            }
         ],
         "response_result": 1
      },
      "license": "-----BEGIN PUBLIC KEY-----\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDXVQmq3+UqbhC3rLCXSEu\/\/miV\nFXhkr+zoK17NTfA9VbdVT95Ag+CLi8hEAnkffpPEacLAIoVjOgtzT4wlWTpkUHCR\nLlVqw6mjJsqF4EWH4b4N\/eJ+7S0O+vAJi7cxscOaU6zs9Dm+lPNvN4AmRi05xOHW\nDhZ8i8+VWEP\/azAO1wIDAQAB\n-----END PUBLIC KEY-----\n"
   }
   ```
   
   
   
5. Pelaporan History

   **Request**
   ```
   curl --request GET \
   --url http://127.0.0.1/CapeeL/Pelaporan \
   --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjFcL3NpbXJzdjJcL2NsaWVudCIsImlhdCI6MTYzMjgxNTQwOCwibmJmIjoxNjMyODE1NDE4LCJleHAiOjE2MzI4MTU0MzgsImF1ZCI6InVzZXJzX2xpYnJhcnkiLCJkYXRhIjp7InVpZCI6IjgxMTM2NTJkLTRjYjctZTg1MC1kNDg3LTI4MWExNzYyMDQyYSIsImZvdG8iOiIiLCJwYXNzd29yZCI6IiQyeSQxMCRGYTlvWU95Yi53dElaMXBMbXN6NGNlRnJtMHowT3d2ZlI2elM3Tzg3bGVVakhuem1VQm94QyIsImVtYWlsIjoidGhleWNhbnRyZXZlYWx1c0BnbWFpbC5jb20iLCJuYW1hIjoiSGVuZHJ5IFRhbmFrYSIsIm5vX2hhbmRwaG9uZSI6IjA4NTI2MTUxMDIwMiIsImxvZ19pZCI6IjMwIn19.jWjDIaXEzYDvZBQbAb10AgzMk_e5JiRmIx67PRQtvxs' \
   --cookie PHPSESSID=63gclihstfp1bgum9pctmg3udm
   ```
   
   **Response**
   ```
   {
      "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjFcL3NpbXJzdjJcL2NsaWVudCIsImlhdCI6MTYzMjgxNjI4OSwibmJmIjoxNjMyODE1NDE4LCJleHAiOjE2MzI4MTYzNDksImF1ZCI6InVzZXJzX2xpYnJhcnkiLCJkYXRhIjp7InVpZCI6IjgxMTM2NTJkLTRjYjctZTg1MC1kNDg3LTI4MWExNzYyMDQyYSIsImZvdG8iOiIiLCJwYXNzd29yZCI6IiQyeSQxMCRGYTlvWU95Yi53dElaMXBMbXN6NGNlRnJtMHowT3d2ZlI2elM3Tzg3bGVVakhuem1VQm94QyIsImVtYWlsIjoidGhleWNhbnRyZXZlYWx1c0BnbWFpbC5jb20iLCJuYW1hIjoiSGVuZHJ5IFRhbmFrYSIsIm5vX2hhbmRwaG9uZSI6IjA4NTI2MTUxMDIwMiIsImxvZ19pZCI6IjMwIn19.zzdq_pYRHTdKG11qRC2ADnYkO0CYhf8jq6o_JRfwfvw",
      "response_package": {
         "response_query": "SELECT lapor.id,lapor.id_jenis,lapor.id_kecamatan,lapor.id_kelurahan FROM lapor WHERE lapor.uid_pegawai = ? AND lapor.deleted_at IS NULL",
         "response_values": [
            "8113652d-4cb7-e850-d487-281a1762042a"
         ],
         "response_data": [
            {
               "id": 1,
               "nama_jenis": "Laporan Kematian",
               "nik" : "123132123123",
               "nama" : "asdasdasdasdas",
               "tgl_submit" "12321313"
            },
            {
               "id": 1,
               "nama_jenis": "Laporan Kematian",
               "nik" : "123132123123",
               "nama" : "asdasdasdasdas",
               "tgl_submit" "12321313"
            },
            {
               "id": 1,
               "nama_jenis": "Laporan Kematian",
               "nik" : "123132123123",
               "nama" : "asdasdasdasdas",
               "tgl_submit" "12321313"
            }
         ],
         "response_result": 1
      },
      "license": "-----BEGIN PUBLIC KEY-----\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDXVQmq3+UqbhC3rLCXSEu\/\/miV\nFXhkr+zoK17NTfA9VbdVT95Ag+CLi8hEAnkffpPEacLAIoVjOgtzT4wlWTpkUHCR\nLlVqw6mjJsqF4EWH4b4N\/eJ+7S0O+vAJi7cxscOaU6zs9Dm+lPNvN4AmRi05xOHW\nDhZ8i8+VWEP\/azAO1wIDAQAB\n-----END PUBLIC KEY-----\n"
   }
   ```
