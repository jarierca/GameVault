#!/bin/bash

function cleanup {
    echo "[!] Deteniendo servicios..."
    kill $DB_PID $BACK_PID $FRONT_PID
    wait $DB_PID $BACK_PID $FRONT_PID
    exit 0
}

trap cleanup SIGINT

docker-compose up &
DB_PID=$!
echo "[!] Iniciando BD: PostgreSQL."

./mvnw compile quarkus:dev &
BACK_PID=$!
echo "[!] Iniciando back: Quarkus dev."

cd ../GameVaultFront
docker-compose up &
FRONT_PID=$!
echo "[!] Iniciando front: React frontal."

sleep infinity

