const base = "https://localhost:5001/api";

const api = {
  base: "local",
  authentificate: base + "/Users/authenticate",
  rooms: base + "/rooms",
  sendEmail: base + "/Email/send"
};

// const base = "https://hotelautomationapi20210407223324.azurewebsites.net/api"

// const api = {
//   base: "local",
//   authentificate: base + "/Users/authenticate",
//   rooms: base + "/rooms"
//   sendEmail: base + "/Email/send"
// };

export default api;