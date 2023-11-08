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

option=("create file" "add Hello word" "replace bash word to world word " "nothing")

select option in "${option[@]}"; do
  case $option in
  "create file")
  create_file
    ;;
  "add 'hello word'")
  add_hello_word
;;

"replace 'bash word'")
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