<?php
    
    require("conection.php");

    $id = $_POST['id'];



    //Verificamos las variables
    $sql = $mysqli->query("SELECT * from LOCALES where owner_local = '$id' ");
    $result = array();
    if($sql->num_rows > 0) {
        while( $row = $sql->fetch_assoc()) {
            $result[] = $row;
        }
    }


    echo json_encode($result);

    
    
?>