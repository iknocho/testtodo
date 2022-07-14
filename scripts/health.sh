#!/usr/bin/env bash

#0 = 해당 스크립트의 프로그램의 이름이 포함된 첫 번째 문자열 ,$1 ~ $N : 매개변수 인수들
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source $(ABSDIR)/profile.sh
source $(ABSDIR)/switch.sh

IDLE_PORT=$(find_idle_port)

echo "> Health Check Start!"
echo "> IDLE_PORT: $IDLE_PORT"
echo "> curl -s http://localhost:$IDLE_PORT/profile "
sleep 10

for RETRY_COUNT in {1..10}
do
  RESPONSE=$(curl -s http://localhost:${IDLE_PORT}/profile)
  UP_COUNT=$(echo ${RESPONSE} | grep 'real' | wc -l)

  if [$(UP_COUNT) -ge 1]
  then # up_count >=1 (real 문자열 포함되었는지 검증)
    echo "> Health check 성공"
    switch_proxy
    break
  else
    echo "> Health check의 응답을 알 수 없거나 혹은 실행 상태가 아닙니다"
    echo "> Health check:${RESPONSE}"
  fi

  if[${RETRY_COUNT} -eq 10]
  then
    echo "> Health check 실패"
    echo "> 엔진엑스에 연결하지 않고 배포를 종료"
    exit 1
  fi

  echo "> Health check 연결 실패. 재시도.."
  sleep 10
done