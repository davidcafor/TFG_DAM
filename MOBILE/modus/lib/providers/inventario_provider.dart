import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:modus/models/inventario.dart';
import 'package:modus/models/inventario_response.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart' as http;
import '../models/tienda.dart';

class InventarioProvider with ChangeNotifier {

  late String ip;
  late String port;
  final apiToken = "a93E3cr6vpO-QMXYP_eJdrkujJR2OgUOQ5nsXa21";

  Future<void> initializeSettings() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    ip = prefs.getString('ip') ?? "";
    port = prefs.getString('port') ?? "";
  }

  Future<List<Inventario>> listaInventario() async {

    await initializeSettings();
    var url = Uri.parse('http://$ip:$port/api/v1/db/data/noco/p_96xp5wgyv3d0fj/Inventario/views/Inventario');
    var headers = {
      'xc-token' : apiToken
    };
    var response = await http.get(url, headers: headers);

    //print(response.body);

    //List<Inventario> listaInventario = InventarioResponse.fromJson(jsonDecode(response.body)).list;
    /*List<Inventario> listaInventario = List<Inventario>.from(jsonDecode(response.body).map((x) => Inventario.fromJson(x)));
    print(listaInventario);
*/
    var jsonData = jsonDecode(response.body);
    InventarioResponse inventarioResponse = InventarioResponse.fromJson(jsonData);
    List<Inventario> listaInventario = inventarioResponse.list;

    return listaInventario;
  }

  Future<List<Inventario>> listaInventarioByProducto({required int idProducto}) async {

    await initializeSettings();
    var url = Uri.parse('http://$ip:$port/api/v1/db/data/noco/p_96xp5wgyv3d0fj/Inventario/views/Inventario?where=(Productos.Id,eq,$idProducto)');
    var headers = {
      'xc-token' : apiToken
    };
    var response = await http.get(url, headers: headers);

    List<Inventario> listaInventarioByProducto = InventarioResponse.fromJson(jsonDecode(response.body)).list;

    return listaInventarioByProducto;
  }
}
