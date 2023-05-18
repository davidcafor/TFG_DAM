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
      body: FutureBuilder(
        future: provider.listaProductos(),
          builder: (BuildContext context, AsyncSnapshot<List<ListElement>> snapshot) {
            if(snapshot.hasData){
              List<ListElement> productos = snapshot.data!;
              return ListView.separated(
                itemCount: productos.length,
                  itemBuilder: (BuildContext context, int index) {
                    ListElement producto = productos[index];

                    return GestureDetector(

                      onTap: () => Navigator.of(context)
                        .pushNamed('detalle', arguments: producto),

                      child: ElementoLista(producto: producto)
                    );
                  }, separatorBuilder: (BuildContext context, int index) {
                    return Divider(
                      thickness: 1,
                    );
              },
              );
            }else{
              return Center(child: CircularProgressIndicator());
            };
          },),
    );
  }
}
