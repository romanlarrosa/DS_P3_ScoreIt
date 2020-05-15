<?php
    
    require("conection.php");

    $busqueda = $_POST['search'];

    //$busqueda = "ps";


    //Verificamos las variables
    $query = "SELECT * FROM LOCALES WHERE (LOWER(nombre_local) LIKE LOWER('%".$busqueda."%') OR LOWER(descripcion) LIKE LOWER('%".$busqueda."%') OR LOWER(ubicacion) LIKE LOWER('%".$busqueda."%'))";
    $sql = $mysqli->query($query);
    $result = array();
    if($sql->num_rows > 0) {
        while( $row = $sql->fetch_assoc()) {
            $result[] = $row;
        }
    }


    echo json_encode($result);

    
    
?>