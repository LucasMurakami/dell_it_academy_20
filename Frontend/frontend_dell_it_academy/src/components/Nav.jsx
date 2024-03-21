import { navLinks } from '../constants'
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';

import './css/Nav.css';

// Componente que renderiza a barra de navegação.
const Nav = () => {

    // Retorna um componente React com a estrutura da barra de navegação.
    return (
    <header>
      <nav className="navbar navbar-expand-lg navbar-custom">
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav">
              {navLinks.map((item, index) => (
                item.dropdown ? (
                  <li className="nav-item dropdown" key={index}>
                    <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">{item.label}</a>
                    <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                    {item.dropdownLinks.map((dropdownLink, subIndex) => (
                        <li key={subIndex}><a className="dropdown-item" href={dropdownLink.href}>{dropdownLink.label}</a></li>
                    ))}
                    </ul>
                  </li>
                ) 
                :
                (
                  <li className="nav-item" key={index}>
                    <a className="nav-link nav-link-customfont" href={item.href}>{item.label}</a>
                </li>
                )
              ))}
            </ul>
          </div>
      </nav>
    </header>
    

    )
    
}

export default Nav
