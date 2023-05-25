const socket = io();

const messageForm = document.getElementById('message-form');
const messageInput = document.getElementById('input-message');
const messagesList = document.getElementById('messages');
const usernameForm = document.getElementById('username-form');
const usernameInput = document.getElementById('input-username');
const chatContainer = document.getElementById('chat-container');

let username = '';

usernameForm.addEventListener('submit', (e) => {
  e.preventDefault();
  username = usernameInput.value;
  if (username) {
    socket.emit('user joined', username);
    usernameForm.style.display = 'none';
    chatContainer.style.display = 'block';
  }
});

messageForm.addEventListener('submit', (e) => {
  e.preventDefault();
  const message = messageInput.value;
  if (message) {
    socket.emit('chat message', message);
    messageInput.value = '';
  }
});

socket.on('user joined', (username) => {
  const message = document.createElement('div');
  message.classList.add('user-joined');
  messagesList.appendChild(message);
});

socket.on('user left', (username) => {
  const message = document.createElement('div');
  message.classList.add('user-left');
  messagesList.appendChild(message);
});

socket.on('chat message', ({ username, message }) => {
  const messageDiv = document.createElement('div');
  messageDiv.classList.add('message');
  if (username === usernameInput.value) {
    messageDiv.classList.add('own-message');
  }
  messageDiv.innerHTML = `
    <div class="message">
      <div class="sender">${username}</div>
      <div class="content">${message}</div>
      <div class="timestamp">${getCurrentTime()}</div>
    </div>
  `;
  messagesList.appendChild(messageDiv);
  messagesList.scrollTop = messagesList.scrollHeight;
});

function getCurrentTime() {
  const now = new Date();
  const hours = now.getHours().toString().padStart(2, '0');
  const minutes = now.getMinutes().toString().padStart(2, '0');
  return `${hours}:${minutes}`;
}

document.addEventListener('DOMContentLoaded', function () {
  openPopup();
});

function openPopup() {
  document.getElementById('popup').style.display = 'block';
}

function closePopup() {
  document.getElementById('popup').style.display = 'none';
}

// Función para imprimir la lista de usuarios conectados
function printConnectedUsers(users) {
  const usersContainer = document.getElementById('users-container');
  const usersList = usersContainer.querySelector('.users-list');

  // Eliminar usuarios anteriores
  while (usersList.firstChild) {
    usersList.firstChild.remove();
  }

  // Agregar usuarios a la lista
  users.forEach((user) => {
    const listItem = document.createElement('div');
    listItem.classList.add('user-item');

    // Agregar circulito verde
    const indicator = document.createElement('div');
    indicator.classList.add('indicator');
    listItem.appendChild(indicator);

    // Agregar nombre de usuario
    const username = document.createElement('div');
    username.textContent = user;
    listItem.appendChild(username);

    usersList.appendChild(listItem);
  });
}


// Evento para recibir la lista de usuarios conectados
socket.on('connected users', (users) => {
  printConnectedUsers(users);
});
