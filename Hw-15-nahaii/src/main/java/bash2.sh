#!/usr/bin/env bash

//question2_of_bash

create_file(){
  for (( i = 0; i < 5; i++ )); do
      touch "file$i.txt"
  done
  echo "5 files created"
}

add_hello_word(){
  for file in file*.txt; do
    echo "Hello word">>"$file"
    done
    echo "added hello word to all files"
}

replace_bash_word(){
    for file in file*.txt; do
  sed -i 's/world/bash/g' "$file"
  done
  echo "replacing is complete"
}
while true; do
  echo " select one of the choice that written below"
  echo "1. create"
  echo "2. add hello word"
  echo "3. replace"

  read -r choice

  case "$choice" in
  1)
  create_file
    ;;
  2)
  add_hello_word
;;

3)
replace_bash_word
;;
"nothing")
break
;;
*)
  echo "invalid!"
  ;;

  esac

done