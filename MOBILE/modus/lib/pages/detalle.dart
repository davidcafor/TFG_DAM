import 'package:flutter/material.dart';
import '../models/inventario_response.dart';
import 'package:provider/provider.dart';

import '../models/inventario.dart';
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
    Producto producto = ModalRoute.of(context)!.settings.arguments as Producto;

    final providerInventario =
        Provider.of<InventarioProvider>(context, listen: false);
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
                  Expanded(
                    child: FutureBuilder(
                      future: providerInventario.listaInventario(),
                      builder: (BuildContext context,
                          AsyncSnapshot<List<Inventario>> snapshot) {
                        if (snapshot.hasData) {

                          //creo objetos para filtrar consulta nocodb
                          List<Inventario> inventarioList = snapshot.data!;
                          List<Tiendas> tiendasFiltradas = inventarioList
                              .where((inventario) =>
                                  inventario.productos.id == producto.id)
                              .map((inventario) => inventario.tiendas)
                              .toList();


                          //ordenar tiendas alfabeticamente
                          tiendasFiltradas.sort((a, b) => a.nombre.compareTo(b.nombre));

                          return ListView.separated(
                            itemCount: tiendasFiltradas.length,
                            itemBuilder: (context, index) {
                              final tienda = tiendasFiltradas[index];
                              final inv = inventarioList[index];
                              return ListTile(
                                  title: Text(tienda.nombre),
                              subtitle: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                              Text('Stock: ${inv.cantidad}'),
                              Text('Última modificación: ${inv.ultimaActualizacion.toString()}'),
                              ],
                              ),
                              );

                            },
                            separatorBuilder:
                                (BuildContext context, int index) {
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
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
