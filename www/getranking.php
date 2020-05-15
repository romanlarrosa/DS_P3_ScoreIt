<?php
    
    require("conection.php");


    //Verificamos las variables

    $sql = $mysqli->query("SELECT locales.*, aux.puntuacion from LOCALES as locales, (SELECT AVG(puntos) as puntuacion, id_local as id FROM PUNTUACIONES GROUP BY id_local) as aux 
    where locales.id_local =  id ORDER BY aux.puntuacion DESC LIMIT 3");

    $result = array();
    if($sql->num_rows > 0) {
        while( $row = $sql->fetch_assoc()) {
            $result[] = $row;
        }
    }


    echo json_encode($result);

    
    
?>