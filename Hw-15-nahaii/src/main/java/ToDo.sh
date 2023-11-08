#!/usr/bin/env bash

ToDo_File="todo.txt"
DELETE_FILE="delete.txt"
COMPLETE_FILE="complete"

touch "ToDo_File" "DELETE_FILE" "COMPLETE_FILE"

# shellcheck disable=SC2120
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


  echo "select one of the choice that written below "
  echo "1. add to do file"
  echo "2. show to do file"
  echo "3. show done file"
  echo "4. delete file"

  read -r choice
  
  case "$choice" in
  1)
    add_to_ToDo_file
    ;;

  2)
    show_To_do
    ;;

  3)
    show_done_task
    ;;

  4)
    delete
    ;;

  *)
    echo " invalid!!"

;;
  esac

