import 'package:flutter/material.dart';

class Menu extends StatelessWidget {
  const Menu({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        children: [
          ListTile(
            leading: Icon(Icons.home),
            title: Text('Inicio'),
            onTap: () {
              Navigator.pushNamed(context, 'home');
            },
          ),
          Divider(), // Línea separadora
          ListTile(
            leading: Icon(Icons.shopping_cart),
            title: Text('Lista Productos'),
            onTap: () {
              Navigator.pushNamed(context, 'listado');
            },
          ),
          Divider(), // Línea separadora
          ListTile(
            leading: Icon(Icons.search),
            title: Text('Búsqueda Productos'),
            onTap: () {
              // Acción al seleccionar la opción 2
            },
          ),
          Divider(), // Línea separadora
          ListTile(
            leading: Icon(Icons.settings),
            title: Text('Ajustes'),
            onTap: () {
              Navigator.pushNamed(context, 'ajustes');
            },
          ),
        ],
      ),
    );
  }
}
