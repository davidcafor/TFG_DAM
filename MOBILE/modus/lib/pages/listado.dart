import 'dart:convert';
import 'dart:io';
import 'dart:typed_data';

import 'package:flutter/material.dart';
import 'package:modus/models/producto.dart';
import 'package:modus/providers/producto_provider.dart';
import 'package:modus/widgets/elemento_lista.dart';
import 'package:modus/widgets/menu.dart';
import 'package:provider/provider.dart';

class Listado extends StatefulWidget {
  const Listado({Key? key}) : super(key: key);

  @override
  State<Listado> createState() => _ListadoState();
}

class _ListadoState extends State<Listado> {

  @override
  Widget build(BuildContext context) {

    final provider = Provider.of<ProductoProvider>(context, listen: false);

    return Scaffold(
      appBar: AppBar(
        title: Text('Listado Productos'),
      ),
      drawer: Menu(),
      body: FutureBuilder<List<Producto>>(
        future: provider.listaProductos(),
        builder: (BuildContext context, AsyncSnapshot<List<Producto>> snapshot) {
          if (snapshot.hasData) {
            List<Producto> productos = snapshot.data!;
            return ListView.builder(
              itemCount: productos.length,
              itemBuilder: (BuildContext context, int index) {
                Producto producto = productos[index];

                return Padding(
                  //padding: EdgeInsets.symmetric(vertical: 10),
                  padding: EdgeInsets.symmetric(vertical: 5, horizontal: 10),
                  child: GestureDetector(
                    onTap: () => Navigator.of(context).pushNamed('detalle', arguments: producto),
                    child: ElementoLista(producto: producto),
                  ),
                );
              },
              itemExtent: 180,
            );
          } else {
            return Center(child: CircularProgressIndicator());
          }
        },
      ),
    );
  }
}