<?php
$json = array();
$mysqli = new mysqli("localhost", "root", "", "travelexperts");

/* check connection */
if ($mysqli->connect_errno) {
    printf("Connect failed: %s\n", $mysqli->connect_error);
    exit();
}

$query = "SELECT * FROM agents";

if ($result = $mysqli->query($query)) {

    /* fetch associative array */
    while($row = $result->fetch_assoc()) {
        $json['agents'][]=$row;
    }

    /* free result set */
    $result->free();
}

/* close connection */
$mysqli->close();


echo json_encode($json);

?>
