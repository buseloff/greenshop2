import React from 'react';
import './cart-item.styles.scss';

const CartItem = ({ item: { price, name, quantity, imageData } }) => (
  <div className='cart-item'>
    <img src={`data:image/png;base64,${imageData}`} alt='item' />
    <div className='item-details'>
      <span className='name'>{name}</span>
      <span className='price'>
        {quantity} x ${price}
      </span>
    </div>
  </div>
);

export default CartItem;
