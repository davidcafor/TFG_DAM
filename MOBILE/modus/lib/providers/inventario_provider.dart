import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:modus/models/inventario.dart';
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

  Future<List<ListInventario>> listaInventario() async {

    await initializeSettings();
    var url = Uri.parse('http://$ip:$port/api/v1/db/data/noco/p_96xp5wgyv3d0fj/Inventario/views/Inventario');
    var headers = {
      'xc-token' : apiToken
    };
    var response = await http.get(url, headers: headers);



    List<ListInventario> listaInventario = Inventario.fromJson(jsonDecode(response.body)).list;


print(listaInventario.length);

    return listaInventario;
  }

  Future<List<ListInventario>> listaInventarioByProducto({required int idProducto}) async {

    await initializeSettings();
    var url = Uri.parse('http://$ip:$port/api/v1/db/data/noco/p_96xp5wgyv3d0fj/Inventario/views/Inventario?where=(Productos.Id,eq,$idProducto)');
    var headers = {
      'xc-token' : apiToken
    };
    var response = await http.get(url, headers: headers);

    List<ListInventario> listaInventarioByProducto = Inventario.fromJson(jsonDecode(response.body)).list;

    return listaInventarioByProducto;
  }
}
