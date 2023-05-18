import 'package:flutter/material.dart';
import 'package:modus/models/inventario.dart';
import 'package:modus/models/tienda.dart';
import 'package:modus/providers/tienda_provider.dart';
import 'package:provider/provider.dart';

import '../models/producto.dart';
import '../providers/inventario_provider.dart';
import '../providers/producto_provider.dart';

class Detalle extends StatefulWidget {
  const Detalle({Key? key}) : super(key: key);

  @override
  State<Detalle> createState() => _DetalleState();
}

class _DetalleState extends State<Detalle> {
  @override
  Widget build(BuildContext context) {

    ListElement producto = ModalRoute.of(context)!.settings.arguments as ListElement;

    final providerInventario = Provider.of<InventarioProvider>(context, listen: false);
    final provider = Provider.of<ProductoProvider>(context, listen: false);

    return Scaffold(
      appBar: AppBar(
        title: Text('Detalles del producto'),
      ),
      body: Column(
        children: [
          Expanded(
            flex: 2,
            child: Container(
              color: Colors.grey,
              child: producto.imagen != null
                  ? Image.network(
                'http://192.168.68.57' + producto.imagen!,
                fit: BoxFit.cover,
              )
                  : Image.asset(
                'assets/images/default.png',
                fit: BoxFit.cover,
              ),
            ),
          ),
          Expanded(
            flex: 3,
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    producto.nombre,
                    style: TextStyle(
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  SizedBox(height: 16),
                  Text(
                    producto.descripcion,
                    style: TextStyle(fontSize: 16),
                  ),
                  SizedBox(height: 16),
                  Text(
                    'Precio: ${producto.precio}€',
                    style: TextStyle(fontSize: 16),
                  ),
                  SizedBox(height: 16),
                  /*Expanded(
                    child: FutureBuilder(
                      //future: providerInventario.listaInventarioByProducto(idProducto: producto.id),
                      future: providerInventario.listaInventario(),
                      builder: (BuildContext context, AsyncSnapshot<List<ListInventario>> snapshot) {
                        if (snapshot.hasData) {
                          List<ListInventario> inventarioList = snapshot.data!;
                          return ListView.separated(
                            itemCount: inventarioList.length,
                            itemBuilder: (BuildContext context, int index) {
                              ListInventario inventario = inventarioList[index];

                              ListElement producto = inventario.productos as ListElement;
                              ListElement productoActual = provider.listaProductosByID(idProducto: producto.id) as ListElement;

                              if(producto == productoActual){
                                return ListTile(
                                  title: Text(inventario.id.toString()),
                                  //subtitle: Text(tienda.direccion),
                                );
                              }else{
                                return Container();
                              }


                            },
                            separatorBuilder: (BuildContext context, int index) {
                              return Divider(
                                thickness: 1,
                              );
                            },
                          );
                        } else {
                          return Center(child: CircularProgressIndicator());
                        }
                      },
                    ),
                  ),*/
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
