<?php
    
    require("conection.php");

    //Verificamos las variables
    $sql = $mysqli->query("SELECT * from USUARIOS where tipo = 'OWNER' and verificado = '0' ");
    $result = array();
    if($sql->num_rows > 0) {
        while( $row = $sql->fetch_assoc()) {
            $result[] = $row;
        }
    }


    echo json_encode($result);

    
    
?>