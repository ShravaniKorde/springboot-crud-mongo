import React from 'react';

const Header: React.FC = () => {
    return (
        <header style={{
            background: 'linear-gradient(90deg, #4b6cb7, #182848)',
            padding: '20px 0',
            color: 'white',
            textAlign: 'center',
            boxShadow: '0 2px 6px rgba(0,0,0,0.2)'
        }}>
            <h1 style={{ margin: 0, fontSize: '2rem', letterSpacing: '1px' }}>
                Student Management App
            </h1>
        </header>
    );
};

export default Header;
