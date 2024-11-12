#!/bin/bash

# Replace 'example.com' with the domain of your target website
TARGET_DOMAIN="example.com"

# Use openssl to get the certificate chain and save it in PEM format
openssl s_client -showcerts -connect ${TARGET_DOMAIN}:443 </dev/null 2>/dev/null | awk '
  /-----BEGIN CERTIFICATE-----/,/-----END CERTIFICATE-----/ {
    print > "c" n ".crt"
    if (/-----END CERTIFICATE-----/) {
      n++
    }
  }
'