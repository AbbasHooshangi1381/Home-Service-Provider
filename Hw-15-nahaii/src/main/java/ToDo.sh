#!/usr/bin/env bash

ToDo_File="todo.txt"
DELETE_FILE="delete.txt"
COMPLETE_FILE="complete"

touch "ToDo_File" "DELETE_FILE" "COMPLETE_FILE"

function add_to_ToDo_file() {
    echo "$1">>"ToDo_File"
    echo "added"
}

function show_To_do() {
     cat  "$ToDo_File"
     echo "showed"
}

function show_done_task() {
    cat "$COMPLETE_FILE"
    echo "show done task"
}

function deleted_file() {
  cat "$DELETE_FILE"
  echo " file is deleted"
    
}

function search_in_ToDo_file() {
    findWord="$1"
    grep "findWord" "ToDo_File"
}

function search_in_delete_file() {
    findWord="$1"
    grep "findWord" "DELETE_FILE"
}

function search_in_complete_file() {
    findWord="$1"
    grep "findWord" "COMPLETE_FILE"
}

while true; do
  echo " select one of the choice that written below"
  echo "1. add to do file"
  echo "2. show to do file"
  echo "3. show done file"
  echo "4. search in ToDo file"
  echo "5. search in Delete file"
  echo "6. search in complete file"
  echo "7. delete file"
  echo "0. exit"

  read -r choice

  case "$choice" in
  1)
    echo " write your task"
    read -r new_work
    add_to_ToDo_file "$new_work"

    ;;

  2)
    show_To_do
    ;;

  3)
    show_done_task
    ;;

  4)
    read -r " write your word" search_word
    echo " the answer is "
    search_in_ToDo_file "$search_word"
    echo
;;
  5)
    read -r " write your word" search_word
    echo " the answer is "
    search_in_delete_file "$search_word"
    echo
;;
  6)
    read -r " write your word" search_word
    echo " the answer is "
    search_in_complete_file "$search_word"
    echo
;;
  7)
    delete
    ;;

  0)
    echo " finish !"
    exit 0
;;
  *)
    echo " invalid!!"

;;
  esac

done

